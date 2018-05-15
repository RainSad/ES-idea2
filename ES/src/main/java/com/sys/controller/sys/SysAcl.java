package com.sys.controller.sys;

import com.sys.entity.param.AclParam;
import com.sys.entity.resdata.JsonData;
import com.sys.service.sys.SysAclService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys/acl")
public class SysAcl {

    Logger log = Logger.getLogger(SysAcl.class);

    @Autowired
    private SysAclService sysAclService;

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

}
