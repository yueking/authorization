package com.yueking.security.demo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

// @Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("time filter is init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter 开始");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        long time = System.currentTimeMillis() - start;
        System.out.println("time filter 耗时:"+time);
        System.out.println("time filter 结束");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("time filter is destroy");
    }
}
