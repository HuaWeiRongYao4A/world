package com.world.producer.serviceimpl;


import com.world.common.entity.User;
import com.world.producer.mapper.UserMapper;
import com.world.producer.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
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

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public int register(String username, String password) {
        String activeCode = UUID.randomUUID().toString().replace("-", "");
        User user = new User(username, password, username, activeCode, (byte) 0);
        userMapper.insert(user);
        //进入队列
        rabbitTemplate.convertAndSend("emailExchange", "topic.user.register.email.key", user);
        return 1;
    }

    @Override
    public int checkUserRegister(User user) {
        return userMapper.checkUserRegister(user);
    }

    @Override
    public int active(User user) {
        return userMapper.active(user);
    }

    @Override
    public User detail(User user) {
        return userMapper.detail(user);
    }

}
