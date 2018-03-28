package com.core.security;

import org.apache.log4j.Logger;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * 在http标签中加入了：<custom-filter ref="securityFilter" before=
 * "FILTER_SECURITY_INTERCEPTOR"/>,用于地址的拦截
 * MySecurityFilter这个类是拦截中一个基本的类，拦截的时候会先通过这里：
 * 
 * @Description:
 * @ClassName: MySecurityFilter
 * @author 孙文祥
 * @date 2017年11月1日 下午5:18:59
 *
 */
public class MySecurityFilter extends AbstractSecurityInterceptor implements Filter {

	Logger log = Logger.getLogger(MySecurityFilter.class);

	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(req, res, chain);
		log.debug("--------MySecurityFilter--------");
		invok(fi);
	}

	private void invok(FilterInvocation fi) throws IOException, ServletException {
		// object为FilterInvocation对象
		// 1.获取请求资源的权限
		// 运行Collection<ConfigAttribute> attributes =
		// SecurityMetadataSource.getAttributes(object);
		// 2.是否拥有权限
		// 获取安全主体。能够强制转换为UserDetails的实例
		// 1) UserDetails
		// Authentication authenticated = authenticateIfRequired();
		// this.accessDecisionManager.decide(authenticated, object, attributes);
		// 用户拥有的权限
		// 2) GrantedAuthority
		// Collection<GrantedAuthority> authenticated.getAuthorities()
		log.debug("用户发送请求！ ");
		InterceptorStatusToken token = null;

		token = super.beforeInvocation(fi);

		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	public Class<? extends Object> getSecureObjectClass() {
		// 以下的MyAccessDecisionManager的supports方面必须放回true,否则会提醒类型错误
		return FilterInvocation.class;
	}

}
