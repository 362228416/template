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
 * ͨ������Service��������
 * ��鷽���Ƿ����Ȩ����֤ע��
 * ����������鵱ǰ�û��Ƿ�߱���ӦȨ��
 * �߱����������÷����������׳��쳣
 * @see com.ydpp.aop.SessionIterceptor
 * Created by 16 on 2015/4/20.
 */
@Aspect
public class AccessControllableInterceptor {

    static ThreadLocal<User> loginUser = new ThreadLocal<>();

    public AccessControllableInterceptor() {
        System.out.println("Ȩ������������");
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
