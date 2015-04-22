package com.ydpp.service.impl;

import com.ydpp.annotation.RequirePermission;
import com.ydpp.constants.Function;
import com.ydpp.constants.Permissions;
import com.ydpp.dao.UserRepository;
import com.ydpp.domain.User;
import com.ydpp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 16 on 2015/4/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    @RequirePermission(function = Function.USER, permissions = Permissions.ADD)
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    @RequirePermission(function = Function.USER, permissions = Permissions.DELETE)
    public void delete(Long id) {
        userRepository.delete(id);
    }
}
