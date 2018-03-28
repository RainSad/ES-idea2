package com.sys.service.index;

import com.core.common.utills.IdToolUtil;
import com.core.dao.MysqlBaseDaoImp;
import com.sys.entity.index.ViewComment;
import com.sys.entity.index.ViewImgUrl;
import com.sys.entity.index.ViewShare;
import com.sys.entity.resdata.ViewShareRes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexService extends MysqlBaseDaoImp<ViewShare> {

	Logger log = Logger.getLogger(IndexService.class);
	// 图片存储文件地址
	@Value("${es.upload.imgPath}")
	private String saveFilePath;

	@SuppressWarnings("unchecked")
	public IndexService() {
		Type superclass = getClass().getGenericSuperclass();
		ParameterizedType type = (ParameterizedType) superclass;
		entityClass = (Class<ViewShare>) type.getActualTypeArguments()[0];
	}

	/**
	 * 
	 * @Description: 图片异步上传 
	 * @Title: uploadImg 
	 * @param 
	 * @param img @param 
	 * @return 
	 * @param 
	 * @throws IllegalStateException 
	 * @param @throws IOException    设定文件
	 * @return Map<String,Object>    返回类型
	 * @throws
	 */
	public Map<String, Object> uploadImg(MultipartFile img) throws IllegalStateException, IOException {

		// 原始名称
		String oldFileName = img.getOriginalFilename(); // 获取上传文件的原名
		log.debug("上传图片，图片名称：" + oldFileName);
		// 上传图片
		if (img != null && oldFileName != null && oldFileName.length() > 0) {
			// 新的图片名称
			String newFileName = IdToolUtil.getUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
			// 新图片
			File newFile = new File(saveFilePath + "\\" + newFileName);
			// 将内存中的数据写入磁盘
			img.transferTo(newFile);
			// 将新图片名称返回到前端
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", "成功啦");
			map.put("url", newFileName);
			log.debug("上传成功，图片名称为：" + newFileName);
			return map;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "图片不合法");
			log.debug("图片上传失败，图片不合法");
			return map;
		}
	}
	
	/**
	 * 根据数据装配返回结果集，估计以后用不到，暂时不删
	* @Description: 
	* @Title: viewShareResTemp 
	* @param @param param
	* @param @param param2
	* @param @param param3
	* @param @return    设定文件 
	* @return ViewShareRes    返回类型 
	* @throws
	 */
	public ViewShareRes viewShareResTemp (ViewShare param, List<ViewImgUrl> param2, List<ViewComment> param3){
		
		ViewShareRes temp = new ViewShareRes();
		temp.setAddress(param.getAddress());
		temp.setClickNum(param.getClickNum());
		temp.setCommentId(param3);
		temp.setCreatTime(param.getCreatTime());
		temp.setId(param.getId());
		temp.setImgUrlId(param2);
		temp.setLikeNum(param.getLikeNum());
		temp.setMessage(param.getMessage());
		temp.setOtherEvaluation(param.getOtherEvaluation());
		temp.setSelfEvaluation(param.getSelfEvaluation());
		temp.setStatus(param.getStatus());
		temp.setTitle(param.getTitle());
		temp.setUserId(param.getUserId());
		
		return temp;
	}
}
