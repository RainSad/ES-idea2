package com.sys.service.user;

import com.core.dao.MysqlBaseDaoImp;
import com.sys.entity.user.UserOperation;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Service
public class UserOperationService extends MysqlBaseDaoImp<UserOperation>{

	@SuppressWarnings("unchecked")
	public UserOperationService() {
		Type superclass = getClass().getGenericSuperclass();
	    ParameterizedType type = (ParameterizedType) superclass;
	    entityClass = (Class<UserOperation>) type.getActualTypeArguments()[0];
	}

	
}
