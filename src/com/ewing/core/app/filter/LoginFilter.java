package com.ewing.core.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LoginFilter implements Filter {
    private static Logger logger = Logger.getLogger(LoginFilter.class);
    private FilterConfig filterConfig = null;
    private String redirectURL = null;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResposne,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResposne;
        String contextPath = request.getContextPath();
        String loginPage = contextPath + "/" + redirectURL;
        String reqUrl = request.getRequestURL().toString();

        if (reqUrl.contains(redirectURL) || reqUrl.endsWith(redirectURL)) {
        } else {

        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        redirectURL = filterConfig.getInitParameter("redirectURL");
    }

}
