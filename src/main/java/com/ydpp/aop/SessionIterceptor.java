package com.ydpp.aop;

import com.ydpp.annotation.RequireLogin;
import com.ydpp.domain.User;
import com.ydpp.exception.ACException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Session拦截
 * 检查当前访问的请求方法是否包含登录注解
 * 如果有则检查当前用户是否登录
 * 没有登录则抛出异常
 * 最后将当前用户信息保存到线程变量中
 * Created by 16 on 2015/4/20.
 */
public class SessionIterceptor extends HandlerInterceptorAdapter {

    public static final String LOGIN_USER = "loginUser";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            User loginUser = (User) request.getSession().getAttribute(LOGIN_USER);
            RequireLogin requireLogin = ((HandlerMethod) handler).getMethodAnnotation(RequireLogin.class);
            if (requireLogin != null && loginUser == null) {
                throw new ACException(401, "Not logged in");
            }
            AccessControllableInterceptor.loginUser.set(loginUser);
        }
        return true;
    }

}
