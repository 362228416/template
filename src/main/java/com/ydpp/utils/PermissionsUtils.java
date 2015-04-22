package com.ydpp.utils;

import com.ydpp.domain.User;

/**
 * 权限验证
 * Created by 16 on 2015/4/21.
 */
public class PermissionsUtils {

    public static boolean hasPermissions(User user, String function, String permissions) {
        // TODO 这里做权限判断处理
        return user != null;
    }

}
