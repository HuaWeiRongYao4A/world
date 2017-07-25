package com.world.producer.mapper;


import com.world.producer.entity.User;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface UserMapper {

    int insert(User user);

    int checkUserRegister(User user);

    int active(User user);

    User detail(User user);
}
