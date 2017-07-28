package com.world.producer.service;


import com.world.common.entity.User;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface UserService {

    int register(String email, String password);

    int checkUserRegister(User user);

    int active(User user);

    User detail(User user);
}
