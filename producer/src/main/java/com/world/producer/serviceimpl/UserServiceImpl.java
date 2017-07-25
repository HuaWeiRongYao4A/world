package com.world.producer.serviceimpl;


import com.world.producer.entity.User;
import com.world.producer.mapper.UserMapper;
import com.world.producer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Administrator on 2017/6/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int register(String email, String password) {
        String activeCode = UUID.randomUUID().toString().replace("-", "");
        String username = email;
        User user = new User(username, password, email, activeCode, (byte) 0);
        userMapper.insert(user);
        return 1;
    }

}
