package com.bittech.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author:chaoqiwen
 * @Date:2019/8/4 16:07
 */
public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("这是编码设置的Filter，放在第二位");
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
