package com.sys.controller.sys;

import com.google.common.collect.Maps;
import com.sys.entity.dto.AclModuleLevelDto;
import com.sys.entity.param.UserParam;
import com.sys.entity.resdata.JsonData;
import com.sys.service.sys.SysRoleService;
import com.sys.service.sys.SysTreeService;
import com.sys.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/users")
public class SysUsers {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysTreeService sysTreeService;

    @Autowired
    private SysRoleService sysRoleService;


    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveUser(UserParam param) {
        sysUserService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateUser(UserParam param) {
        sysUserService.update(param);
        return JsonData.success();
    }

    /**
     * 根据用户id获取权限点树和角色列表
     *
     * @param userId
     * @return
     */
    @RequestMapping("acls.json")
    @ResponseBody
    public JsonData acls(@RequestParam("userId") String userId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("acls", sysTreeService.userTree(userId));
        map.put("roles", sysRoleService.getRoleListByUserId(userId));
        return JsonData.success(map);
    }
}
