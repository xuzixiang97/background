package com.tempetek.business.example.userandrole.controller;

import com.tempetek.annotation.Record;
import com.tempetek.annotation.RecordType;
import com.tempetek.business.example.userandrole.entity.UserAndRoleInfo;
import com.tempetek.business.example.userandrole.service.IMyUserAndRoleService;
import com.tempetek.core.AjaxMessage;
import com.tempetek.orm.AbstractBasicController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息管理
 * @author xuzixiang
 * @date 2019-07-07
 */
@Controller
@RequestMapping(value = "/example/userAndRole")
public class UserAndRoleController implements AbstractBasicController {

    @Autowired
    private IMyUserAndRoleService iMyUserAndRoleService;

    /**
     * 保存角色用户
     * @param model
     * @param ids 选中的列
     *
     */
    @Record(module = "角色用户管理", methodType = RecordType.DEL, description = "保存角色用户")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public AjaxMessage insert(ModelMap model,Long[] ids,Long roleId) {
        try {
            List<UserAndRoleInfo> userAndRoleInfoList = new ArrayList<UserAndRoleInfo>();

            for(Long userid : ids){
                UserAndRoleInfo userAndRoleInfo = new UserAndRoleInfo();
                userAndRoleInfo.setRoleId(roleId);
                userAndRoleInfo.setUserId(userid);
                userAndRoleInfoList.add(userAndRoleInfo);
            }

            iMyUserAndRoleService.insertList(userAndRoleInfoList);
            return AjaxMessage.success("保存成功!", null);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error("保存失败!");
        }
    }

    /**
     * 角色用户删除
     * @param model
     * @param ids 主键数组
     * @return 返回操作结果
     */
    @Record(module = "角色用户管理", methodType = RecordType.DEL, description = "删除角色用户")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxMessage delete(ModelMap model, Long[] ids, Long roleId) {
        try {
            List<UserAndRoleInfo> userAndRoleInfoList = new ArrayList<UserAndRoleInfo>();

            for (Long userid : ids) {
                UserAndRoleInfo userAndRoleInfo = new UserAndRoleInfo();
                userAndRoleInfo.setRoleId(roleId);
                userAndRoleInfo.setUserId(userid);
                userAndRoleInfoList.add(userAndRoleInfo);
            }

            iMyUserAndRoleService.deleteByUserIdAndRoleids(userAndRoleInfoList);
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

        }
    }

}