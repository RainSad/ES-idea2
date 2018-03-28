package com.sys.service.user;

import com.core.dao.MysqlBaseDaoImp;
import com.sys.entity.user.UserInfo;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Service
public class UserInfoService extends MysqlBaseDaoImp<UserInfo>{

	@SuppressWarnings("unchecked")
	public UserInfoService() {
		Type superclass = getClass().getGenericSuperclass();
	    ParameterizedType type = (ParameterizedType) superclass;
	    entityClass = (Class<UserInfo>) type.getActualTypeArguments()[0];
	}

	
}
