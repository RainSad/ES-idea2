package com.sys.service.user;

import com.core.dao.MysqlBaseDaoImp;
import com.sys.entity.user.UserLog;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Service
public class UserLogService extends MysqlBaseDaoImp<UserLog>{

	@SuppressWarnings("unchecked")
	public UserLogService() {
		Type superclass = getClass().getGenericSuperclass();
	    ParameterizedType type = (ParameterizedType) superclass;
	    entityClass = (Class<UserLog>) type.getActualTypeArguments()[0];
	}

	
}
