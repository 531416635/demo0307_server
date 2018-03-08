package com.xiao.demo.filter;

/**
 * 描述：
 * 作者： yaoyuxiao
 * 创建时间： 2018/2/8  13:32
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName="sessionFilter",urlPatterns={"*.do","*.html"})

public class SessionFilter implements Filter {

    private Logger logger= LoggerFactory.getLogger(SessionFilter.class);


    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
//        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes( ) ).getRequest( );
//        HttpSession session = request.getSession();
        HttpServletRequest req = (HttpServletRequest) arg0;
        HttpSession session=req.getSession();
        String uri=req.getRequestURI()+"?" + req.getQueryString();
        XssHttpServletRequestFilter xssRequest = new XssHttpServletRequestFilter((HttpServletRequest)req);
        if(uri.indexOf("authCallBack")!=-1||uri.indexOf("appToApp")!=-1
                ||uri.indexOf("userDefaultAuth")!=-1||uri.indexOf("/check/")!=-1
                ||uri.indexOf("notify_url")!=-1||uri.indexOf("return_url")!=-1){//登陆页面则跳过权限判断
            arg2.doFilter(xssRequest, arg1);
            return ;
        }

        String userid="";


        if(StringUtils.isEmpty(userid)){
            if(uri.startsWith("/hospital")){
                uri=uri.replace("/hospital", "");
            }
            logger.debug("url:"+uri+",user:"+userid);
            session.setAttribute("redirect_url", uri);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/auth/userDefaultAuth.do");
            // 跳转到登陆页面
            dispatcher.forward(req, arg1 );
            return ;
        }

        arg2.doFilter(xssRequest, arg1);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}

