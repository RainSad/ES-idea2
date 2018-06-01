package com.sys.controller.sys;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.sys.entity.param.AclParam;
import com.sys.entity.resdata.JsonData;
import com.sys.entity.sys.SysRole;
import com.sys.entity.sys.SysUser;
import com.sys.service.sys.SysAclService;
import com.sys.service.sys.SysRoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/acl")
public class SysAcl {

    Logger log = Logger.getLogger(SysAcl.class);

    @Autowired
    private SysAclService sysAclService;

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("save")
    @ResponseBody
    public JsonData saveAclModule(AclParam param) {
        sysAclService.save(param);
        return JsonData.success();
    }

    @RequestMapping("update")
    @ResponseBody
    public JsonData updateAclModule(AclParam param) {
        sysAclService.update(param);
        return JsonData.success();
    }

    /**
     * 根据权限点查看被分配给哪些用户和角色
     *
     * @return
     */
    @RequestMapping("/acls.json")
    @ResponseBody
    public JsonData acls(@RequestParam("aclId") String aclId) {
        Preconditions.checkNotNull(aclId, "权限点id为空！");
        Map<String, Object> map = Maps.newHashMap();
        List<SysRole> roleList = sysRoleService.getRoleListByAclId(aclId);
        List<SysUser> userList = sysRoleService.getUserListByRoleList(roleList);
        //删除密码
        userList.forEach(sysUser -> sysUser.setPassword(""));
        map.put("users", userList);
        map.put("roles", roleList);
        return JsonData.success(map);
    }


}
