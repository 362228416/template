package com.ydpp.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 需要登录
 * 在处理方法上加上注解表面该请求需要登录
 * Created by 16 on 2015/4/21.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireLogin {
}
