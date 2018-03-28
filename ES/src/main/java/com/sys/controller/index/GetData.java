package com.sys.controller.index;

import com.sys.entity.index.ViewShare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @Description: 主页的数据加载类
 * @ClassName: GetData
 * @author 孙文祥
 * @date 2017年10月20日 下午4:55:48
 *
 */
@Controller
public class GetData {


    /**
	 * 获取主页轮播图
	* @Description: 
	* @Title: indexDataCarousel 
	* @param @return    设定文件 
	* @return Page<ViewShare>    返回类型 
	* @throws
	 */
	@RequestMapping("index/data/carousel")
	@ResponseBody
	public Page<ViewShare> indexDataCarousel() {

		 Sort sort = new Sort(Direction.DESC, "creatTime");
		 Pageable pageable = new PageRequest(0, 3);
		 
		 //viewShareRepositoryImp.findAll(new Specification<ViewShare>((root,query,cb) -> {}), pageable);
		return null;
	}

}
