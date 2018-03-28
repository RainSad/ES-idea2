package com.core.dao;

import com.sys.entity.resdata.QueryResultPage;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

	void save(T entity);

	void update(T entity);

	void delete(T entity);

	void delete(Serializable id);

	void deleteAll();

	void batchSave(List<T> list);

	void batchUpdate(List<T> list);

	void batchDelete(List<T> list);

	List<T> findById(Serializable id);

	List<T> findAll();

	QueryResultPage<T> findByPage(Integer pageNo, Integer pageSize);

	QueryResultPage<T> findByPage(Integer pageNo, Integer pageSize, T entity);

	QueryResultPage<T> findByPage(Integer pageNo, Integer pageSize, Map<String, String> orderby);

	QueryResultPage<T> findByPage(Integer pageNo, Integer pageSize, T where,
                                  Map<String, String> orderby);
	

}
