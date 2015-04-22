package com.ydpp.service;

import com.ydpp.domain.User;

/**
 * Created by 16 on 2015/4/20.
 */
public interface UserService {

    User add(User user);

    void delete(Long id);

}
