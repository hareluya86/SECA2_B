/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vincent.a.lee
 */
//@WebFilter("/*")
public class PrependFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String pathInfo = req.getPathInfo();
        
        System.out.println("Request URI = "+requestURI+", ContextPath = "+contextPath+", servletPath = "
            +servletPath+", pathInfo = "+pathInfo);
        
        if(!servletPath.contains("/faces/*") &&
                pathInfo == null || pathInfo.isEmpty()){ //no servlet patterns recognized yet
            
            pathInfo = servletPath;
            servletPath = "/program";
            requestURI = servletPath+pathInfo;
            
            //RequestDispatcher rd = req.getRequestDispatcher(requestURI);
            System.out.println("Rewrite URI = "+requestURI+", ContextPath = "+contextPath+", servletPath = "
            +servletPath+", pathInfo = "+pathInfo);
            //rd.forward(request, response);
            
            resp.sendRedirect(requestURI);
            return;
        }else{
            chain.doFilter(req, resp);
        }
        
    }

    @Override
    public void destroy() {
        
    }
    
}
