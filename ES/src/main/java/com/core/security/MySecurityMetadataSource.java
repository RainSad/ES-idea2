package com.core.security;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("mySecurityMetadataSource")
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	// 由spring调用
	Logger log = Logger.getLogger(MySecurityMetadataSource.class);
	@Autowired
	private MySecurityDaoImp dao;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	/*
	 * public MySecurityMetadataSource() {
	 * 
	 * loadResourceDefine(); }
	 */

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	// 载入全部资源与权限的关系
	private void loadResourceDefine() {
		/*if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			
			
			Map<String,List<SysResources>> findResources = dao.findResource();
			//存储用户-资源url对应关系
			for(Map.Entry<String, List<SysResources>> entry : findResources.entrySet()) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				ConfigAttribute configAttribute = new SecurityConfig(entry.getKey());
				for(SysResources temp : entry.getValue()) {
					configAttributes.add(configAttribute);
					resourceMap.put(temp.getUrl(), configAttributes);
				}
			}
			
			// 以权限名封装为Spring的security Object

		}
		log.debug("权限资源相应关系：" + JSON.toJSONString(resourceMap));*/
	}

	// 返回所请求资源所须要的权限
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		log.info("requestUrl is " + requestUrl);
		if (resourceMap == null) {
			loadResourceDefine();
		}
		log.debug("通过资源定位到的权限：" + resourceMap.get(requestUrl));
		return resourceMap.get(requestUrl);
	}

}