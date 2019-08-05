package com.tempetek.business.example.myrole.controller;

import com.tempetek.annotation.Record;
import com.tempetek.annotation.RecordType;
import com.tempetek.business.example.myrole.entity.RoleInfo;
import com.tempetek.business.example.myrole.service.IMyRoleService;
import com.tempetek.core.AjaxMessage;
import com.tempetek.core.tree.TreeModel;
import com.tempetek.core.tree.TreeNode;
import com.tempetek.core.util.ObjectUtil;
import com.tempetek.orm.AbstractBasicController;
import com.tempetek.safety.context.UserContext;
import com.tempetek.safety.manager.AuthenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色信息管理
 * @author xuzixiang
 * @date 2019-07-07
 */
@Controller
@RequestMapping(value = "/example/role")
public class MyRoleController implements AbstractBasicController {

	@Autowired
	private AuthenManager authenManager;

	@Autowired
	private IMyRoleService iMyRoleService;

	/**
	 * 跳转角色管理页面
	 * @param model
	 * @param code 模块编号
	 * @param title 模块标题
	 * @return 账户信息管理页面
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(ModelMap model, String code, String title) {
		model.put("code", code);
		model.put("title", title);
		return "example/role/tree";
	}

	/**
	 * 加载角色信息 生成角色树
	 * @param model
	 * 返回tree的节点信息
	 * @return 节点信息
	 */
	@RequestMapping(value = "tree", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeModel> tree(ModelMap model) {
		List<RoleInfo> roleInfoList = iMyRoleService.findByCondition(null);
		List<TreeModel> treeModelList = new ArrayList<>();

		for (RoleInfo roleInfo : roleInfoList) {
			TreeModel treeModel = new TreeModel();
			treeModel.setId(roleInfo.getId());
			treeModel.setText(roleInfo.getName());
			treeModel.setPid(roleInfo.getPid());
			treeModelList.add(treeModel);
		}
		return TreeNode.getTree(treeModelList);
	}

	/**
	 * 跳转角色tree新增页面
	 * @param model
	 * @param id 主键ID
	 * @return 账户新增页面路径
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model, Long id) {
		if(id!=null){
			RoleInfo roleInfo = new RoleInfo();
			roleInfo.setPid(id);
			model.put("roleInfo", roleInfo);
		}
		return "example/role/index";
	}

	/**
	 * 跳转role修改页面
	 * @param model
	 * @param id 主键ID
	 * @return 账户新增页面路径
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(ModelMap model, Long id) {
		if(id != null){
			RoleInfo roleInfo = iMyRoleService.findById(id);
			model.put("roleInfo", roleInfo);
		}
		return "example/role/index";
	}

	/**
	 * 保存角色的新增/修改
	 * @param model
	 * @param condition 账户信息对象
	 * @return 返回操作结果
	 */
	@Record(module = "角色管理", methodType = RecordType.SAVE, description = "保存角色")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage save(ModelMap model, RoleInfo condition) {
		try {
			RoleInfo roleInfo = null;
			UserContext userContext = authenManager.getCurrentUser();

			if (condition.getId() != null) {
				roleInfo = iMyRoleService.findById(condition.getId());
				ObjectUtil.copyNoClude(condition, roleInfo, new String[]{"creator", "create_time"});
				roleInfo.setModifier(userContext.getModifier());
				roleInfo.setModifiedTime(new Date());
			} else {
				roleInfo = condition;
				roleInfo.setCreator(userContext.getCreator());
				roleInfo.setCreateTime(new Date());
			}

			iMyRoleService.save(roleInfo);
			return AjaxMessage.success("保存成功!", roleInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxMessage.error("保存失败!");
		}
	}

	/**
	 * 角色的删除
	 * @param model
	 * @param id 主键
	 * @return 返回操作结果
	 */
	@Record(module = "角色管理", methodType = RecordType.DEL, description = "删除角色")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage delete(ModelMap model, Long id) {
		try {
			iMyRoleService.deleteById(id);
			return AjaxMessage.success("删除成功!", null);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxMessage.error("删除失败!");
		}
	}

	/**
	 * 跳转分配用户页面
	 * @param model
	 * @param id 主键ID
	 * @return 分配用户页面路径
	 */
	@RequestMapping(value = "userDialog", method = RequestMethod.GET)
	public String userDialog(ModelMap model, Long id) {
		if(id != null){
			RoleInfo roleInfo = new RoleInfo();
			roleInfo.setId(id);
			model.put("roleInfo", roleInfo);
		}
		return "example/user/userDialog";
	}

	/**
	 * 编码唯一性校验
	 * @param id
	 * @param code
	 * @return 返回检验结果
	 */
	@RequestMapping(value = "codeUnique", method = RequestMethod.POST)
	@ResponseBody
	public boolean codeUnique(Long id, String code) {
		RoleInfo role = iMyRoleService.findByCode(code);

		if(role != null && !role.getId().equals(id)){
			return false;
		}
		return  true;
	}

	/**
	 * 转化数据字典
	 * @param obj 实体类
	 */
	@Override
    public void translate(Object obj) {
		if(obj != null){
		}
    }

}