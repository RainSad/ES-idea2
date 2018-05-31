package com.sys.controller.sys;

import com.core.common.utills.StringUtils;
import com.sys.entity.param.RoleParam;
import com.sys.entity.resdata.JsonData;
import com.sys.service.sys.SysRolAclService;
import com.sys.service.sys.SysRoleService;
import com.sys.service.sys.SysTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("sys/role")
public class SysRole {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysTreeService sysTreeService;

    @Autowired
    private SysRolAclService sysRolAclService;

    @RequestMapping("save")
    @ResponseBody
    public JsonData saveAclModule(RoleParam param) {
        sysRoleService.save(param);
        return JsonData.success();
    }

    @RequestMapping("update")
    @ResponseBody
    public JsonData updateAclModule(RoleParam param) {
        sysRoleService.update(param);
        return JsonData.success();
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @RequestMapping("list")
    public JsonData List() {
        return JsonData.success(sysRoleService.getAll());
    }

    /**
     * 获取角色权限树
     * @param roleId
     * @return
     */
    @RequestMapping("roleTree.json")
    @ResponseBody
    public JsonData roleTree(@RequestParam("roleId") String roleId) {
        return JsonData.success(sysTreeService.roleTree(roleId));
    }

    /**
     * 修改保存分配的权限点
     *
     * @param roleId
     * @return
     */
    @RequestMapping("changeAcls.json")
    @ResponseBody
    public JsonData changeAcls(@RequestParam("roleId") String roleId, @RequestParam(value = "aclIds", required = false, defaultValue = "") String aclIds) {
        List<String> aclIdsList = StringUtils.spiltToListString(aclIds, ",");
        return JsonData.success();
    }
}
