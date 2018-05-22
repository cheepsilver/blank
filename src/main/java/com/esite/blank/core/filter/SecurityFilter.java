package com.esite.blank.core.filter;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(1)//@Order注解表示执行过滤顺序，值越小，越先执行
@WebFilter(filterName = "securityFilter", urlPatterns = "/*")//过滤器注解

public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("securityFilter_______test");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
