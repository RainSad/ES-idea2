package com.core.common.filter;


import com.core.common.webUtils.RequestHolder;
import com.sys.entity.sys.SysUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        SysUser sysUser = (SysUser) req.getSession().getAttribute("user");
        //用户信息为空
        if(null == sysUser){
            String path = "/login.html";
            resp.sendRedirect(req.getContextPath() + path);
            return;
        }
        RequestHolder.add(sysUser);
        RequestHolder.add(req);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
