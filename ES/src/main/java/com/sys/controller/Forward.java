package com.sys.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName: index
 * @Description: (首页的各种操作)
 * @author 孙文祥
 * @date 2017年8月11日 下午5:10:04
 *
 */
@Controller
public class Forward {

	Logger log = Logger.getLogger(Forward.class);


	@RequestMapping("/")
	public String toIndex() {
		log.debug(" -- > ['toIndex'] --> [ '/' ]  --> [ '/WEB-INF/view/index/index.html' ]");
		return "index/index";
	}

    @RequestMapping("/index")
    public String toIndex2() {
		log.debug(" -- > ['toIndex'] --> [ '/toIndex' ]  --> [ '/WEB-INF/view/index/index.html' ]");
		return "index/index";
	}


	@RequestMapping("/toLogin")
	public String toLogin() {
		log.debug(" -- > ['toLogin'] --> [ '/toLogin' ]  --> [ '/WEB-INF/view/index/login.html' ]");
		return "sys/admin/login";
	}

    @RequestMapping("/toRigister")
    public String toRegister() {
		log.debug(" -- > ['toRegister'] --> [ '/toRigister' ]  --> [ '/WEB-INF/view/index/register.html' ]");
		return "sys/admin/register";
	}

    @RequestMapping("/toDetail")
    public String toDetail() {
		log.debug(" -- > ['toDetail'] --> [ '/toRigister' ]  --> [ '/WEB-INF/view/index/detail.html' ]");
		return "index/detail";
	}

	@RequestMapping("/toSysDept")
	public String toSysDept() {
		log.debug(" -- > ['toSysDept'] --> [ '/toSysDept' ]  --> [ '/WEB-INF/view/sys/dept/dept.html' ]");
		return "sys/dept/dept";
	}

	@RequestMapping("/toSysMain")
	public String toSysMain() {
		log.debug(" -- > ['toSysMain'] --> [ '/toSysMain' ]  --> [ '/WEB-INF/view/sys/toSysMain.html' ]");
		return "sys/main";
	}

	@RequestMapping("/toSysUser")
	public String toSysUserList() {
		log.debug(" -- > ['toSysUser'] --> [ '/toSysUser' ]  --> [ '/WEB-INF/view/sys/user/user.html' ]");
		return "sys/user/user";
	}

	@RequestMapping("/toAclModule")
	public String toAclModule() {
		log.debug(" -- > ['toAclModule'] --> [ '/toAclModule' ]  --> [ '/WEB-INF/view/sys/aclModule/user.html' ]");
		return "sys/aclModule/";
	}

    @RequestMapping("/toWebSocket")
    public String webSocket() {

		return "index/websockettest";
	}

}
