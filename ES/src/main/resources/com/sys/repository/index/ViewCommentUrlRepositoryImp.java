package com.sys.repository.index;

import com.sys.entity.index.ViewComment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ViewCommentUrlRepositoryImp extends CrudRepository<ViewComment, String> {

	List<ViewComment> findByid(String id);
}
