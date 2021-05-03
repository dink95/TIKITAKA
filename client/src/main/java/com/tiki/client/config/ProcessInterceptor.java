package com.tiki.client.config;


import com.tiki.client.domain.MemberDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ProcessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        boolean result = false;

        try {
            String mbrId = null;

            if(  request.getSession().getAttribute("mbrId") != null) {
                mbrId =   request.getSession().getAttribute("mbrId").toString();
            }

            if(mbrId == null ){
                if(isAjaxRequest(request)) {
                    response.sendError(405);
                    return false;
                }else{
                    response.sendRedirect("/login");
                    result =  false;
                }
            }else{
                result =  true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
        return result;
    }

    private boolean isAjaxRequest(HttpServletRequest req) {
        String header = req.getHeader("AJAX");
        if ("true".equals(header)){
            return true;
        }else{
            return false;
        }
    }
}
