package com.sys.controller.sys;

import com.core.common.utills.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sys.entity.param.RoleParam;
import com.sys.entity.resdata.JsonData;
import com.sys.entity.sys.SysUser;
import com.sys.service.sys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("sys/role")
public class SysRole {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysTreeService sysTreeService;

    @Autowired
    private SysRolAclService sysRolAclService;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Autowired
    private SysUserService sysUserService;

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
    @RequestMapping("roles.json")
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
        sysRolAclService.changeRoleAcls(roleId, aclIdsList);
        return JsonData.success();
    }

    /**
     * 修改保存分配的权限点
     *
     * @param roleId
     * @return
     */
    @RequestMapping("changeUsers.json")
    @ResponseBody
    public JsonData changeUsers(@RequestParam("roleId") String roleId, @RequestParam(value = "userIds", required = false, defaultValue = "") String userIds) {
        List<String> userIdsList = StringUtils.spiltToListString(userIds, ",");
        sysRoleUserService.changeRoleUsers(roleId, userIdsList);
        return JsonData.success();
    }


    /**
     * 获取用户数据，选中和未选中的用户
     *
     * @param roleId
     * @return
     */
    @RequestMapping("users.json")
    @ResponseBody
    public JsonData users(@RequestParam("roleId") String roleId) {
        //已选择的用户列表
        List<SysUser> selectedUserList = Lists.newArrayList(sysRoleUserService.getListByRoleId(roleId));
        //所有的用户列表
        List<SysUser> allUsersList = Lists.newArrayList(sysUserService.getAll());
        //没有选择的用户列表
        List<SysUser> unselectedUserList = Lists.newArrayList();

        //通过流快速生成set
        Set<String> selectedUserIdSet = selectedUserList.stream().map(sysUser -> sysUser.getId()).collect(Collectors.toSet());
        allUsersList.forEach(sysUser -> {
            if (sysUser.getStatus().equals("1") && !selectedUserIdSet.contains(sysUser.getId())) {
                sysUser.setPassword("");
                unselectedUserList.add(sysUser);
            }
        });

        //过滤操作，不显示状态时不为1的用户
        //selectedUserList.stream().filter(SysUser -> !SysUser.getStatus().equals("1")).collect(Collectors.toList());
        //设置用户密码未空串
        selectedUserList.forEach(a -> a.setPassword(""));
        Map<String, List<SysUser>> map = Maps.newHashMap();
        map.put("selected", selectedUserList);
        map.put("unselected", unselectedUserList);
        return JsonData.success(map);
    }
}
