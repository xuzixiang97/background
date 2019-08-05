package com.tempetek.business.example.mymenu.controller;

import com.tempetek.business.example.mymenu.entity.MenuInfo;
import com.tempetek.business.example.mymenu.service.IMyMenuService;
import com.tempetek.business.example.myuser.entity.UserInfo;
import com.tempetek.business.example.roleandmenu.entity.RoleAndMenuInfo;
import com.tempetek.business.example.roleandmenu.service.IRoleAndMenuService;
import com.tempetek.core.tree.TreeModel;
import com.tempetek.core.tree.TreeNode;
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
 * 菜单信息管理
 * @author xuzixiang
 * @date 2019-07-07
 */
@Controller
@RequestMapping(value = "/example/menu")
public class MyMenuController implements AbstractBasicController {

    @Autowired
    private IMyMenuService iMyMenuService;

    @Autowired
    private IRoleAndMenuService iRoleAndMenuService;

    /**
     * 根据点击的角色，生成所对应的菜单权限tree
     * @param model
     * 返回tree的节点信息
     * @return 节点信息
     */
    @RequestMapping(value = "tree", method = RequestMethod.POST)
    @ResponseBody
    public List<TreeModel> tree(ModelMap model, Long id) {
        List<RoleAndMenuInfo> RoleAndMenuInfoList = iRoleAndMenuService.findByRoleid(id);
        List<MenuInfo> menuInfoList = iMyMenuService.findByCondition(null);
        List<TreeModel> treeModelList = new ArrayList<>();

        for (MenuInfo menuInfo : menuInfoList) {
            TreeModel treeModel = new TreeModel();

            for(RoleAndMenuInfo ram : RoleAndMenuInfoList){
                if(menuInfo.getId().equals(ram.getMenuId())){
                    treeModel.setChecked(true);
                }
            }

            treeModel.setId(menuInfo.getId());
            treeModel.setText(menuInfo.getCode()+" "+menuInfo.getText());
            treeModel.setIconCls(menuInfo.getIconcls());
            treeModel.setPid(menuInfo.getPid());
            treeModelList.add(treeModel);
        }
        return TreeNode.getTree(treeModelList);
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