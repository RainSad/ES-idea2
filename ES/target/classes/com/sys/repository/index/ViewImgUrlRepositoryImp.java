package com.sys.repository.index;

import com.sys.entity.index.ViewImgUrl;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ViewImgUrlRepositoryImp extends CrudRepository<ViewImgUrl, String>{

	List<ViewImgUrl> findByid(String id);

}
