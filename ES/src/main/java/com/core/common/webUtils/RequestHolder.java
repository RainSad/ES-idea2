package com.core.common.webUtils;

import com.sys.entity.sys.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * 多线程保存数据，高并发，互相之间不会影响
 */
public class RequestHolder {


    private static final ThreadLocal<SysUser> userHolder = new ThreadLocal<SysUser>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public static void add(SysUser sysUser){
        userHolder.set(sysUser);
    }

    public static SysUser getCurrentUser(){
        return userHolder.get();
    }

    public static void add(HttpServletRequest httpServletRequest){
        requestHolder.set(httpServletRequest);
    }

    public static HttpServletRequest getCurrentRequest(){
        return requestHolder.get();
    }

    public static void remove(){
        userHolder.remove();
        requestHolder.remove();
    }
}
