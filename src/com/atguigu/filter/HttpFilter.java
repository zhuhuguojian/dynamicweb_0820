package com.atguigu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/10/20
 * @Time 11:13
 */
public abstract class HttpFilter implements Filter {

    private FilterConfig filterConfig;

    public void destroy() {
    }

    /**
     * 重载doFilter
     * @param request
     * @param response
     * @param chain
     */
    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)/* throws ServletException, IOException*/;

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //chain.doFilter(req, resp);
        //类型转换
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //调用重载doFilter()方法
        doFilter(request,response,chain);
    }

    public void init(FilterConfig config) throws ServletException {
        filterConfig = config;
    }

    /**
     * 获取FilterConfig对象
     * @return
     */
    public FilterConfig getFilterConfig(){
        return filterConfig;
    }

    /**
     * 获取ServletContext对象
     * @return
     */
    public ServletContext getServletContext(){
        return filterConfig.getServletContext();
    }

}
