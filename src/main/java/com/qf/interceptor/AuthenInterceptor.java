package com.qf.interceptor;

import com.qf.bean.User;
import com.qf.constant.SSMconstant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 刘少楠
 * @Date 2019/05/26
 */
public class AuthenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();

        User user = (User)session.getAttribute(SSMconstant.USER);

        if(user!=null){

            return  true;
        }else{
            response.sendRedirect(request.getContextPath()+"/user/login");
            return false;
        }


    }





    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
