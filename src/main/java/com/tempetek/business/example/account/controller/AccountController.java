package com.tempetek.business.example.account.controller;

import com.alibaba.druid.util.StringUtils;
import com.tempetek.annotation.Record;
import com.tempetek.annotation.RecordType;
import com.tempetek.business.example.account.entity.AccountInfo;
import com.tempetek.business.example.account.service.IAccountService;
import com.tempetek.core.AjaxMessage;
import com.tempetek.core.Pagination;
import com.tempetek.core.editor.DateEditor;
import com.tempetek.core.util.ObjectUtil;
import com.tempetek.orm.AbstractBasicController;
import com.tempetek.platform.organization.person.entity.Person;
import com.tempetek.platform.organization.person.service.IPersonService;
import com.tempetek.platform.work.dictionary.entity.DictionaryData;
import com.tempetek.platform.work.dictionary.service.IDictionaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 账户信息管理
 * @author songhailong
 * @date 2018-05-04 15:05:17
 */
@Controller
@RequestMapping(value = "/example/account")
public class AccountController implements AbstractBasicController {

	@Autowired
	private IAccountService iAccountService;

	@Autowired
	private IPersonService iPersonService;

	@Autowired
	private IDictionaryDataService iDictionaryDataService;

	@Value("${dict.user.locked}")
	private String LOCKED;
	
	@Value("${dict.accountType}")
	private String ACCOUNT_TYPE;

	/**
	 * 跳转账户信息管理页面
	 * @param model
	 * @param code 模块编号
	 * @param title 模块标题
	 * @return 账户信息管理页面
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(ModelMap model, String code, String title) {
		model.put("code", code);
		model.put("title", title);
		return "example/account/list";
	}

	/**
	 * 加载账户信息列表
	 * @param model
	 * @param pagination 分页参数
	 * @param condition  查询条件
	 * @return 分页数据集合
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<AccountInfo> list(ModelMap model, Pagination<AccountInfo> pagination, AccountInfo condition) {
		iAccountService.findByPager(pagination, condition);
		for (AccountInfo accountInfo : pagination.getRows()) {
			this.translate(accountInfo);
		}
		return pagination;
	}

	/**
	 * 跳转账户新增/修改页面
	 * @param model
	 * @param id 主键ID
	 * @return 账户新增/修改页面路径
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(ModelMap model, Long id) {
		if (id != null) {
			AccountInfo accountInfo = iAccountService.findById(id);
			model.put("accountInfo", accountInfo);
		}
		return "example/account/index";
	}

	/**
	 * 账户信息新增/修改
	 * @param model
	 * @param condition 账户信息对象
	 * @return 返回操作结果
	 */
	@Record(module = "账户管理", methodType = RecordType.SAVE, description = "保存账户")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage save(ModelMap model, AccountInfo condition) {
		try {
			AccountInfo accountInfo = null;
			if (condition.getId() != null) {
				accountInfo = iAccountService.findById(condition.getId());
				ObjectUtil.copy(condition, accountInfo);
			} else {
				accountInfo = condition;
			}
			iAccountService.save(accountInfo);
			return AjaxMessage.success("保存成功!", accountInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxMessage.error("保存失败!");
		}
	}

	/**
	 * 账户信息删除
	 * @param model
	 * @param ids 主键数组
	 * @return 返回操作结果
	 */
	@Record(module = "账户管理", methodType = RecordType.DEL, description = "删除账户")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage delete(ModelMap model, Long[] ids) {
		try {
			iAccountService.deleteByIds(ids);
			return AjaxMessage.success("删除成功!", null);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxMessage.error("删除失败!");
		}
	}

	/**
	 * 转化数据字典
	 * @param obj 实体类
	 */
	@Override
	public void translate(Object obj) {
		if(obj != null){
			AccountInfo accountInfo = (AccountInfo) obj;

			if (accountInfo.getPersonid() != null) {
				Person person = iPersonService.findById(accountInfo.getPersonid());
				accountInfo.setPersonStr(person == null ? "" : person.getName());
			}

			if (accountInfo.getLocked()!=null) {
				DictionaryData dictionaryData = iDictionaryDataService.findByCode(LOCKED, String.valueOf(accountInfo.getLocked()));
				accountInfo.setLockedStr(dictionaryData == null ? "" : dictionaryData.getName());
			}

			if (!StringUtils.isEmpty(accountInfo.getType())) {
				DictionaryData dictionaryData = iDictionaryDataService.findByCode(ACCOUNT_TYPE, accountInfo.getType());
				accountInfo.setTypeStr(dictionaryData == null ? "" : dictionaryData.getName());
			}
		}
	}
	
	@InitBinder    
	public void initBinder(WebDataBinder binder) {    
        binder.registerCustomEditor(Date.class, new DateEditor());
	}

}