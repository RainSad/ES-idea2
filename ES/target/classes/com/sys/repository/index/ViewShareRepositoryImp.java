package com.sys.repository.index;

import com.sys.entity.index.ViewShare;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ViewShareRepositoryImp extends CrudRepository<ViewShare,String>, PagingAndSortingRepository<ViewShare, String>, JpaSpecificationExecutor<ViewShare>{
	
	List<ViewShare> findByType(String type);
	

}
