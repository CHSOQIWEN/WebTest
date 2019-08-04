package com.bittech.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author:chaoqiwen
 * @Date:2019/8/4 16:07
 */
public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("这是登录检查的Filter，需要放在第一位");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("这是登录检查的Filter，用户已经成功访问到Servlet");

    }
}
