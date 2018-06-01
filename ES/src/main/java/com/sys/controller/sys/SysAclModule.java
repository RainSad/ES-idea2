package com.sys.controller.sys;

import com.alibaba.fastjson.JSON;
import com.sys.entity.dto.AclModuleLevelDto;
import com.sys.entity.param.AclModuleParam;
import com.sys.entity.resdata.JsonData;
import com.sys.repository.sys.SysAclModuleRepositoryImp;
import com.sys.service.sys.SysAclModuleService;
import com.sys.service.sys.SysTreeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sys/aclModule")
public class SysAclModule {

    Logger log = Logger.getLogger(SysAclModule.class);
    @Autowired
    private SysTreeService sysTreeService;

    @Autowired
    private SysAclModuleService sysAclModuleService;

    @RequestMapping("save")
    @ResponseBody
    public JsonData saveAclModule(AclModuleParam param){
        sysAclModuleService.save(param);
        return JsonData.success();
    }

    @RequestMapping("update")
    @ResponseBody
    public JsonData updateAclModule(AclModuleParam param){
        sysAclModuleService.update(param);
        return JsonData.success();
    }

    @RequestMapping("delete")
    @ResponseBody
    public JsonData deleteAclModule(String id) {
        sysAclModuleService.delete(id);
        return JsonData.success();
    }


    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree() {
        List<AclModuleLevelDto> dtoList = sysTreeService.aclMuduleTree();

        return JsonData.success(dtoList);
    }

}
