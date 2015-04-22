package com.ydpp.aop;

import com.ydpp.annotation.RequirePermission;
import com.ydpp.domain.User;
import com.ydpp.exception.ACException;
import com.ydpp.utils.PermissionsUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 通过拦截Service方法调用
 * 检查方法是否包含权限验证注解
 * 如果包含则检查当前用户是否具备相应权限
 * 具备则调用允许该方法，否则将抛出异常
 * @see com.ydpp.aop.SessionIterceptor
 * Created by 16 on 2015/4/20.
 */
@Aspect
public class AccessControllableInterceptor {

    static ThreadLocal<User> loginUser = new ThreadLocal<>();

    public AccessControllableInterceptor() {
        System.out.println("权限拦截器启用");
    }

    @Pointcut("execution(public * com.ydpp.service.*.*(..))")
    public void callCURDService() {}

    @Around("callCURDService()")
    public Object callCURDServiceAround(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        Class[] parameterTypes = ((MethodSignature)pjp.getSignature()).getMethod().getParameterTypes();
        Method invokeMethod = pjp.getTarget().getClass().getMethod(methodName, parameterTypes);
        RequirePermission requirePermission = invokeMethod.getAnnotation(RequirePermission.class);
        if (requirePermission != null) {
            User loginUser = AccessControllableInterceptor.loginUser.get();
            if (loginUser == null) {
                throw new ACException("Anonymous user no permission to access this method " + methodName);
            }
            if (!PermissionsUtils.hasPermissions(loginUser, requirePermission.function(), requirePermission.permissions())) {
                throw new ACException("Current user no permission to access this method " + methodName);
            }
        }

        Object result = pjp.proceed();
        return result;
    }

}
