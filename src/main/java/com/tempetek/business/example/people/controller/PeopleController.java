package com.tempetek.business.example.people.controller;

import com.tempetek.annotation.Record;
import com.tempetek.annotation.RecordType;
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
 * 人员信息管理
 * @author xuzixiang
 * @date 2019-07-02
 */
@Controller
@RequestMapping(value = "/example/person")
public class PeopleController implements AbstractBasicController {

	@Autowired
	private AuthenManager authenManager;

	@Autowired
	private IPeopleService iPeopleService;

	@Autowired
	private IDictionaryDataService iDictionaryDataService;

	@Value("${dict.person.sex}")
	private String TP_PERSON_SEX;

	@Value("${dict.person.duty}")
	private String TP_PERSON_DUTY;

	@Value("${dict.person.state}")
	private String TP_PERSON_STATE;

	/**
	 * 跳转人员信息管理页面
	 * @param model
	 * @param code 模块编号
	 * @param title 模块标题
	 * @return 账户信息管理页面
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(ModelMap model, String code, String title) {
		model.put("code", code);
		model.put("title", title);
		return "example/person/list";
	}

	/**
	 * 加载人员信息列表
	 * @param model
	 * @param pagination 分页参数
	 * @param condition  查询条件
	 * @return 分页数据集合
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<PeopleInfo> list(ModelMap model, Pagination<PeopleInfo> pagination, PeopleInfo condition) {
		pagination=iPeopleService.findByPager(pagination, condition);

		for (PeopleInfo peopleInfo : pagination.getRows()) {
			this.translate(peopleInfo);
		}
		return pagination;
	}

	/**
	 * 跳转人员新增/修改页面
	 * @param model
	 * @param id 主键ID
	 * @return 账户新增/修改页面路径
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(ModelMap model, Long id) {
		if (id != null) {
			PeopleInfo peopleInfo = iPeopleService.findById(id);
			model.put("peopleInfo", peopleInfo);
		}
		return "example/person/index";
	}

	/**
	 * 人员信息新增/修改
	 * @param model
	 * @param condition 账户信息对象
	 * @return 返回操作结果
	 */
	@Record(module = "人员管理", methodType = RecordType.SAVE, description = "保存人员")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage save(ModelMap model, PeopleInfo condition) {
		try {
			PeopleInfo peopleInfo = null;
			UserContext userContext = authenManager.getCurrentUser();

			if (condition.getId() != null) {
				peopleInfo = iPeopleService.findById(condition.getId());
				ObjectUtil.copyNoClude(condition, peopleInfo, new String[]{"creator", "create_time"});
				peopleInfo.setModifier(userContext.getModifier());
				peopleInfo.setModifiedTime(new Date());
			} else {
				peopleInfo = condition;
				peopleInfo.setCreator(userContext.getCreator());
				peopleInfo.setCreate_time(new Date());
			}

			iPeopleService.save(peopleInfo);
			return AjaxMessage.success("保存成功!", peopleInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxMessage.error("保存失败!");
		}
	}

	/**
	 * 人员信息删除
	 * @param model
	 * @param ids 主键数组
	 * @return 返回操作结果
	 */
	@Record(module = "人员管理", methodType = RecordType.DEL, description = "删除人员")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage delete(ModelMap model, Long[] ids) {
		try {
			iPeopleService.deleteByIds(ids);
			return AjaxMessage.success("删除成功!", null);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxMessage.error("删除失败!");
		}
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
		PeopleInfo people = iPeopleService.findByCode(code);

		if(people != null && !people.getId().equals(id)){
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
			PeopleInfo peopleInfo = (PeopleInfo) obj;

			if (peopleInfo.getSex()!=null) {
				DictionaryData dictionaryData = iDictionaryDataService.findByCode(TP_PERSON_SEX, String.valueOf(peopleInfo.getSex()));
				peopleInfo.setSexStr(dictionaryData == null ? "" : dictionaryData.getName());
			}

			if (peopleInfo.getDuty()!=null) {
				DictionaryData dictionaryData = iDictionaryDataService.findByCode(TP_PERSON_DUTY, String.valueOf(peopleInfo.getDuty()));
				peopleInfo.setDutyStr(dictionaryData == null ? "" : dictionaryData.getName());
			}

			if (peopleInfo.getState()!=null) {
				DictionaryData dictionaryData = iDictionaryDataService.findByCode(TP_PERSON_STATE, String.valueOf(peopleInfo.getState()));
				peopleInfo.setStateStr(dictionaryData == null ? "" : dictionaryData.getName());
			}
		}
	}

}