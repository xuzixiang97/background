package com.tempetek.business.example.roleandmenu.controller;

import com.tempetek.annotation.Record;
import com.tempetek.annotation.RecordType;
import com.tempetek.business.example.myuser.entity.UserInfo;
import com.tempetek.business.example.roleandmenu.service.IRoleAndMenuService;
import com.tempetek.core.AjaxMessage;
import com.tempetek.orm.AbstractBasicController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 角色菜单信息管理
 * @author xuzixiang
 * @date 2019-07-07
 */
@Controller
@RequestMapping(value = "/example/roleAndMenu")
public class RoleAndMenuController implements AbstractBasicController {

    @Autowired
    private IRoleAndMenuService iRoleAndMenuService;

    /**
     * menu保存
     * @param model
     * @param ids 主键数组
     * @return 返回操作结果
     */
    @Record(module = "菜单管理", methodType = RecordType.SAVE, description = "保存菜单")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxMessage save(ModelMap model, Long[] ids, Long roleId) {
        try {
            iRoleAndMenuService.saveMenuTree(ids,roleId);
            return AjaxMessage.success("保存成功!", null);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error("保存失败!");
        }
    }

    /**
     * 转化数据字典
     * @param obj 实体类
     */
    @Override
    public void translate(Object obj) {
        if(obj != null){
            UserInfo userInfo = (UserInfo) obj;
        }
    }

}