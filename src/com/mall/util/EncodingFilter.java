package com.mall.util;

import java.io.IOException;  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
//@WebFilter(filterName="encodingFilter",urlPatterns={"*.do","*.xhtm"})  
public class EncodingFilter implements Filter {  
      public void init(FilterConfig config) throws ServletException{}  
      public void destroy(){}  
      public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException  
        {  
            request.setCharacterEncoding("utf-8");  
            response.setCharacterEncoding("utf-8");
            EncodingRequest encodingRequest = new EncodingRequest(
                    (HttpServletRequest) request);
            chain.doFilter(encodingRequest, response); 
        }  
  
}  
