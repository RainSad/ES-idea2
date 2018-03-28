package com.core.security;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 * MyAccessDecisionManager这个类。就是获取到请求资源的角色后推断用户是否有这个权限能够訪问这个资源。
 * 假设获取到的角色是null，那就放行通过，这主要是对于那些不须要验证的公共能够訪问的方法。就不须要权限了。
 * 
 * 能够直接訪问。
 * 
 * @Description:
 * @ClassName: MyAccessDecisionManager
 * @author 孙文祥
 * @date 2017年11月1日 下午5:24:29
 *
 */
@Service("myAccessDecisionManager")
public class MyAccessDecisionManager implements AccessDecisionManager {
	Logger log = Logger.getLogger(MyAccessDecisionManager.class);

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		// TODO Auto-generated method stub
		// 假设相应资源没有找到角色 则放行
//		if (configAttributes.isEmpty()) {
//
//			return;
//		}

		log.debug("object is a URL:" + object.toString()); // object is a URL.
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ca.getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needRole.equals(ga.getAuthority())) { // ga is user's role.
					return;
				}
			}
		}
		throw new AccessDeniedException("no right");

	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
