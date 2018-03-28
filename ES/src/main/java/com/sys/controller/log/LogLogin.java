package com.sys.controller.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.core.common.canstant.Canstant;
import com.core.common.utills.IdToolUtil;
import com.sys.entity.user.UserInfo;
import com.sys.entity.user.UserLog;
import com.sys.service.user.UserLogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LogLogin {

	@Autowired
	private UserLogService userLogService;

	Logger log = Logger.getLogger(LogLogin.class);

	// 还有游客和登陆用户的逻辑没有写完，明天继续写

	/**
	 * 
	 * @Title:logLoginInfo 
	 * @Description: 
	 * @param     {
	 * "Ip":"119.79.149.180", "Isp":"湖北省武汉市 长城宽带", "Browser":"Google Chrome
	 * 60.0.3112.90", "OS":"Windows NT", "QueryResult":1 } 
	 * @return void   返回类型 
	 * @throws
	 */
	@RequestMapping("log/saveLoginInfo")
	@ResponseBody
	public String logLoginInfo(@RequestParam(value = "info") String info, HttpServletRequest request,
			ModelMap model) {
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Object isLogin = session.getAttribute(Canstant.IS_LOGIN);
		JSONObject obj = JSON.parseObject(info);
		String UserLogId = IdToolUtil.getUUID();// 保存数据库的id 记录下来后面可能会用到
		// 登陆用户的插入操作 插入用户【id】和用户名【username】
		if (userInfo != null && isLogin == null) {
			UserLog userLog = getUserLog(obj, userInfo, UserLogId);
			userLogService.save(userLog);
			session.setAttribute(Canstant.IS_LOGIN, "logined");
log.debug("  [DEBUG ]" + info + "已保存到数据库");
			return "{'result' : 'success' , 'userName' : '" + userLog.getUserName() + "'}";
		}
		// 未登录用户的插入插座，插入用户名 【游客】
		if (userInfo == null && isLogin == null) {
			UserLog userLog = getUserLog(obj, userInfo, UserLogId);
			userLog.setUserName("游客");// 游客登陆
			userLogService.save(userLog);
log.debug("  [DEBUG ]" + info + "已保存到数据库  =====");
			return "{'result' : 'success' , 'userName' : '" + userLog.getUserName() + "' }";
		}
		return "{'Result' : ''}";
	}

	public UserLog getUserLog(JSONObject obj, UserInfo userInfo, String id) {
		UserLog userLog = new UserLog();
		if (obj != null) {
			userLog.setBrowser(obj.getString("Browser"));
			userLog.setId(id);
			userLog.setLoginAddr(obj.getString("Isp"));
			userLog.setLoginIp(obj.getString("Ip"));
			userLog.setLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			userLog.setOs(obj.getString("OS"));
		}

		if (userInfo != null) {
			userLog.setUserId(userInfo.getId());
			userLog.setUserName(userInfo.getUserName());
		}

		return userLog;
	}
}
