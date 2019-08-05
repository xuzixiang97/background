package com.tempetek.business.example.myuser.controller;

import com.tempetek.annotation.Record;
import com.tempetek.annotation.RecordType;
import com.tempetek.business.example.myuser.entity.UserInfo;
import com.tempetek.business.example.myuser.service.IMyUserService;
import com.tempetek.business.example.people.entity.PeopleInfo;
import com.tempetek.business.example.people.service.IPeopleService;
import com.tempetek.core.AjaxMessage;
import com.tempetek.core.Pagination;
import com.tempetek.core.util.ObjectUtil;
import com.tempetek.orm.AbstractBasicController;
import com.tempetek.platform.work.dictionary.entity.DictionaryData;
import com.tempetek.platform.work.dictionary.service.IDictionaryDataService;
import com.tempetek.safety.context.UserContext;
import com.tempetek.safety.manager.AuthenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 用户信息管理
 * @author xuzixiang
 * @date 2019-07-03
 */
@Controller
@RequestMapping(value = "/example/user")
public class UserController implements AbstractBasicController {

	@Autowired
	private AuthenManager authenManager;

	@Autowired
	private IMyUserService iMyUserService;

	@Autowired
	private IPeopleService iPeopleService;

	@Autowired
	private IDictionaryDataService iDictionaryDataService;

	@Value("${dict.user.locked}")
	private String LOCKED;

	/**
	 * 跳转用户信息管理页面
	 * @param model
	 * @param code 模块编号
	 * @param title 模块标题
	 * @return 用户信息管理页面
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(ModelMap model, String code, String title) {
		model.put("code", code);
		model.put("title", title);
		return "example/user/list";
	}

	/**
	 * 加载用户信息列表
	 * @param model
	 * @param pagination 分页参数
	 * @param condition  查询条件
	 * @return 分页数据集合
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<UserInfo> list(ModelMap model, Pagination<UserInfo> pagination, UserInfo condition) {
		iMyUserService.findByPager(pagination, condition);

		for (UserInfo userInfo : pagination.getRows()) {
			this.translate(userInfo);
		}
		return pagination;
	}

	/**
	 * 跳转用户新增/修改页面
	 * @param model
	 * @param id 主键ID
	 * @return 账户新增/修改页面路径
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(ModelMap model, Long id) {
		if (id != null) {
			UserInfo userInfo = iMyUserService.findById(id);
			model.put("userInfo", userInfo);
		}
		return "example/user/index";
	}

	/**
	 * 用户信息新增/修改
	 * @param model
	 * @param condition 账户信息对象
	 * @return 返回操作结果
	 */
	@Record(module = "用户管理", methodType = RecordType.SAVE, description = "保存用户")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage save(ModelMap model, UserInfo condition) {
		try {
			UserInfo userInfo = null;
			UserContext userContext = authenManager.getCurrentUser();

			if (condition.getId() != null) {
				userInfo = iMyUserService.findById(condition.getId());
				ObjectUtil.copyNoClude(condition, userInfo, new String[]{"creator", "createTime","password"});
				userInfo.setModifier(userContext.getModifier());
				userInfo.setModifiedTime(new Date());
			} else {
				userInfo = condition;
				userInfo.setCreator(userContext.getCreator());
				userInfo.setCreateTime(new Date());
				userInfo.setPassword(authenManager.encrypt("000000"));
			}

			iMyUserService.save(userInfo);
			return AjaxMessage.success("保存成功!", userInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxMessage.error("保存失败!");
		}
	}

	/**
	 * 用户信息删除
	 * @param model
	 * @param ids 主键数组
	 * @return 返回操作结果
	 */
	@Record(module = "用户管理", methodType = RecordType.DEL, description = "删除用户")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage delete(ModelMap model, Long[] ids) {
		try {
			iMyUserService.deleteByIds(ids);
			return AjaxMessage.success("删除成功!", null);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxMessage.error("删除失败!");
		}
	}

	/**
	 * 用户名称唯一性校验
	 * @param id
	 * @param username
	 * @return 返回检验结果
	 */
	@RequestMapping(value = "usernameUnique", method = RequestMethod.POST)
	@ResponseBody
	public boolean usernameUnique(Long id, String username) {
        if(iMyUserService.findByUserName(username) != null && !iMyUserService.findByUserName(username).getId().equals(id)){
            return false;
        }
		return true;
	}

    /**
     * 根据点击的role节点，加载所对应的用户
     * @param model
     * 加载点击节点后的rolrId,根据此id查找user
     * @return 分页信息
     */
    @RequestMapping(value = "listByRoleId", method = RequestMethod.POST)
    @ResponseBody
    public Pagination<UserInfo> listByRoleId(ModelMap model, Pagination<UserInfo> pagination, UserInfo condition,Long id) {
        condition.setRoleId(id);
        iMyUserService.findByPagerRole(pagination, condition);

        for (UserInfo userInfo : pagination.getRows()) {
            this.translate(userInfo);
        }
        return pagination;
    }

    /**
     * 加载角色未分配的用户信息列表
     * @param model
     * @param pagination 分页参数
     * @param condition  查询条件
     * @param condition  角色id
     * @return 分页数据集合
     */
    @RequestMapping(value = "userList", method = RequestMethod.POST)
    @ResponseBody
    public Pagination<UserInfo> userList(ModelMap model, Pagination<UserInfo> pagination, UserInfo condition, Long id) {
        condition.setRoleId(id);
        iMyUserService.findByPagerNotRole(pagination, condition);

        for (UserInfo userInfo : pagination.getRows()){
            this.translate(userInfo);
        }
        return pagination;
    }

    /**
	 * 转化数据字典
	 * @param obj 实体类
	 */
	@Override
	public void translate(Object obj) {
        if(obj != null){
            UserInfo userInfo = (UserInfo) obj;

            if (userInfo.getPersonId() != null) {
                PeopleInfo people = iPeopleService.findById(userInfo.getPersonId());
                userInfo.setPersonStr(people == null ? "" : people.getName());
            }

            if (userInfo.getIsLocked()!=null) {
                DictionaryData dictionaryData = iDictionaryDataService.findByCode(LOCKED, String.valueOf(userInfo.getIsLocked()));
                userInfo.setIsLockedStr(dictionaryData == null ? "" : dictionaryData.getName());
            }
        }
	}

}