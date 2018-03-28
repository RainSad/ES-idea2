package com.sys.controller.sys;

import com.alibaba.fastjson.JSON;
import com.core.common.webUtils.NetworkUtil;
import com.sys.entity.dto.DeptLevelDto;
import com.sys.entity.param.DeptParam;
import com.sys.entity.resdata.JsonData;
import com.sys.service.sys.SysTreeService;
import com.sys.service.sys.SysDepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/sys/dept")
public class sysDept {

    Logger log = Logger.getLogger(sysDept.class);

    @Autowired
    private SysDepartmentService sysDepartmentService;
    @Autowired
    private SysTreeService sysTreeService;

    @RequestMapping("/save")
    @ResponseBody
    public JsonData saveDept(DeptParam deptParam, HttpServletRequest requert) throws IOException {
        sysDepartmentService.save(deptParam);
        log.info(JSON.toJSONString(deptParam));
        return JsonData.success();
    }

    @RequestMapping("/del")
    @ResponseBody
    public JsonData delDept(String ids) throws IOException {
        log.info(ids);
        //sysDepartmentService.save(deptParam);
        return JsonData.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public JsonData updateDept(DeptParam deptParam) {
        sysDepartmentService.update(deptParam);
        log.info(JSON.toJSONString(deptParam));
        return JsonData.success();
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree() {
        List<DeptLevelDto> dtoList = sysTreeService.deptTree();

        return JsonData.success(dtoList);
    }


}
