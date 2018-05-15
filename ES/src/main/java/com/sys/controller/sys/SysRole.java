package com.sys.controller.sys;

import com.sys.entity.param.RoleParam;
import com.sys.entity.resdata.JsonData;
import com.sys.service.sys.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sys/role")
public class SysRole {

    @Autowired
    private SysRoleService sysRoleService;

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

    @RequestMapping("list")
    public JsonData List() {
        return JsonData.success(sysRoleService.getAll());
    }
}