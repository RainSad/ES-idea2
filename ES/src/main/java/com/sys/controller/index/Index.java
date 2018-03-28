package com.sys.controller.index;

import com.core.common.webUtils.ApplicationContextHelper;
import com.core.common.webUtils.BeanValidator;
import com.core.common.utills.IdToolUtil;
import com.sys.entity.index.ViewImgUrl;
import com.sys.entity.index.ViewShare;
import com.sys.entity.resdata.JSONResponse;
import com.sys.entity.resdata.JsonData;
import com.sys.entity.resdata.TestVo;
import com.sys.entity.sys.SysRole;
import com.sys.entity.user.UserInfo;
import com.sys.repository.index.ViewImgUrlRepositoryImp;
import com.sys.repository.index.ViewShareRepositoryImp;
import com.sys.repository.sys.SysRoleRepositoryImp;
import com.sys.service.index.IndexService;
import com.sys.service.sys.SysTreeService;
import com.sys.service.user.UserInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: 分发都在这里
 * @ClassName: Index
 * @author 孙文祥
 * @date 2017年9月18日 上午8:39:13
 *
 */
@Controller
public class Index {

	// @Autowired
	// private BaseDaoImp baseDaoImp;
	// 图片服务器地址
	@Value("${es.upload.imgRealPath}")
	private String imgRealPath;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private IndexService IndexService;
	@Autowired
	private ViewShareRepositoryImp viewShareRepositoryImp;
	@Autowired
	private ViewImgUrlRepositoryImp viewImgUrlRepositoryImp;

    @Autowired
    private SysTreeService sysTreeService;

	Logger log = Logger.getLogger(Index.class);

	@RequestMapping("index/login")
	public String login(@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "passWord", defaultValue = "") String passWord, ModelMap data,
			HttpServletRequest request, ModelMap model) {


		// =================================
		List<UserInfo> info = userInfoService.findById("37c0c52095164aa4981f4aafd6a8354b");
		HttpSession session = request.getSession(true);
		session.setAttribute("userInfo", info.get(0));

		// ===================================

		/*
		 * QueryResult<User> findByPage = userService.findByPage(-1, -1, user);//
		 * pageNo和pageSize都为-1得时候不分页 if (findByPage.getTotalRow() > 0 && findByPage !=
		 * null) { if (findByPage.getData().get(0).getPassWord().equals(passWord)) {
		 * List<UserInfo> info =
		 * userInfoService.findById(findByPage.getData().get(0).getId()); if(info.size()
		 * > 0) { //System.out.println(ip); HttpSession session =
		 * request.getSession(true); session.setAttribute("userInfo", info.get(0));
		 * model.addAttribute("userInfo", JSON.toJSONString(info)); return "index"; } }
		 * else { model.addAttribute("error", "密码错误"); return "login"; } }
		 */
		model.addAttribute("error", "无此用户");
		return "login";

	}

	/**
	 * 
	 * @Description: 图片上传 @Title: indexUploadImg @param @param img @param @param
	 * request @param @return @param @throws IllegalStateException @param @throws
	 * IOException    设定文件 @return Map<String,Object>    返回上传后的文件名称并返回到前台 @throws
	 */
	@RequestMapping("index/upload/img")
	@ResponseBody
	public Map<String, Object> indexUploadImg(MultipartFile img, @RequestParam(value = "id") String id,
			@RequestParam(value = "imgUrlId") String imgUrlId,
			HttpServletRequest request) throws IllegalStateException, IOException {

		log.debug(" -- > ['indexUploadImg'] --> [ 'index/upload/img' ]  --> [ 'map' ]");
		// 上传图片
		Map<String, Object> uploadImg = IndexService.uploadImg(img);
		//再添加到viewimgurl表中
		if(!imgUrlId.isEmpty()) {
			ViewImgUrl entity2 = new ViewImgUrl();
			entity2.setId(imgUrlId);
			entity2.setImgUrl(imgRealPath + "/" + uploadImg.get("url").toString());
			viewImgUrlRepositoryImp.save(entity2);
		}else {
			log.debug("图片id缺失");
		}
		return uploadImg;
	}

	/**
	 * 
	 * @Description:上传分享内容
	 * @Title: indexUploadMessage 
	 * @param @param
	 * titleInput 
	 * @param @param messageDetail 
	 * @param @return    设定文件
	 *  @return
	 * String    返回类型 @throws
	 */
	@RequestMapping("index/upload/message")
	@ResponseBody
	public String indexUploadMessage(@RequestParam(value = "id") String id,
			@RequestParam(value = "titleInput") String titleInput,
			@RequestParam(value = "messageDetail") String messageDetail) {

		ViewShare entity = new ViewShare();
		entity.setId(id);
		// entity.setUserId(userId); TODO
		entity.setCreatTime(new Date());
		entity.setTitle(titleInput);
		entity.setMessage(messageDetail);
		if (!id.isEmpty() && !titleInput.isEmpty() && !messageDetail.isEmpty()) {
			viewShareRepositoryImp.save(entity);
		}
		log.debug(" -- > ['indexUploadMessage'] --> [ 'index/upload/getId' ]  --> [ 'JSONResponse' ]");
		return null;
	}

	/**
	 * 
	* @Description: 从后台加载id
	* @Title: getIndexUploadGetId 
	* @param @return    设定文件 
	* @return JSONResponse    返回类型 
	* @throws
	 */
	@RequestMapping("index/upload/getId/{id}")
	@ResponseBody
	public JSONResponse getIndexUploadGetId(@PathVariable("id") String id) {
		//重新获取id时删除数据库之前存储的数据
		if(!id.isEmpty()) {
			viewShareRepositoryImp.delete(id);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", IdToolUtil.getUUID());
		map.put("imgUrlId", IdToolUtil.getUUID());
		map.put("commentId", IdToolUtil.getUUID());
		//初始化viewshare表，不初始化，外键关联的viewImgUrl表和comment表无法插入数据
		ViewShare entity = new ViewShare();
		entity.setId(map.get("id"));
		entity.setImgUrlId(map.get("imgUrlId"));
		entity.setCommentId(map.get("commentId"));
		viewShareRepositoryImp.save(entity);
		log.debug(" -- > ['getIndexUploadGetId'] --> [ 'index/upload/getId' ]  --> [ " + map.get("id") + " / "+ map.get("imgUrlId") + " / " + map.get("commentId") + " ]");
		return new JSONResponse(map.size() > 0 ? 1 : 0, map, map.size());
	}

	@RequestMapping("index/viewShare/detail/{id}")
	@ResponseBody
	public JSONResponse getViewShareDetail(@PathVariable("id") String id) {
		
		ViewShare findOne = viewShareRepositoryImp.findOne(id);
		
		return new JSONResponse(findOne == null ? 0 :1, findOne);
		
	}

	@RequestMapping("/hello.json")
	@ResponseBody
	public JsonData hello() {
		log.info("hello");
		throw new RuntimeException();
	}

	@RequestMapping("/validate.json")
	@ResponseBody
	public JsonData validateJson(TestVo vo) {
		log.info("validate");
		SysRoleRepositoryImp temp = ApplicationContextHelper.popBean(SysRoleRepositoryImp.class);
		SysRole one = temp.findOne("1");
		System.out.println(one.getRoleCode());
		BeanValidator.check(vo);
		return JsonData.success("success validate");
	}
}
