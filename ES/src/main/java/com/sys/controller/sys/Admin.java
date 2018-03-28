package com.sys.controller.sys;

import com.core.common.utills.MD5Util;
import com.sys.entity.sys.SysUser;
import com.sys.service.sys.SysUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Admin {

    Logger log = Logger.getLogger(Admin.class);

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, ModelMap map){
        Logger log = Logger.getLogger(Admin.class);
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        SysUser sysUser = sysUserService.findByKeyWord(username);
        String errorMsg = "";

        String ret = request.getParameter("ret");

        if (StringUtils.isEmpty(username)) {
            errorMsg = "用户名不能为空";
        } else if (StringUtils.isEmpty(password)) {
            errorMsg = "密码不能为空";
        } else if (sysUser == null) {
            errorMsg = "用户不存在";
        } else if (!MD5Util.encrypt(password).equals(sysUser.getPassword())) {
            errorMsg = "用户名或密码错误";
        } else if (Integer.parseInt(sysUser.getStatus()) != 1) {
            errorMsg = "用户已被冻结，请联系管理员";
        } else {
            //login succes
            request.getSession().setAttribute("user", sysUser);
            if (!StringUtils.isEmpty(ret)) {
                return "redirect:" + ret;
            } else {
                return "redirect:toSysMain";
            }
        }

        map.addAttribute("error", errorMsg);
        map.addAttribute("username", username);
        if (!StringUtils.isEmpty(ret)) {
            map.addAttribute("ret", ret);

        }
        return "forward:toLogin";
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:toLogin";
    }
}
