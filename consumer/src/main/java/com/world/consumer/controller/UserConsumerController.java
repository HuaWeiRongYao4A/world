package com.world.consumer.controller;

import com.world.consumer.remote.UserRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description : 用户远程调用Controller
 * @CreateTime : 2017/07/24
 */
@RestController
@RequestMapping("web/user")
public class UserConsumerController {

    @Autowired
    private UserRemote userRemote;

    @PostMapping("register")
    public String register(@RequestParam String username, @RequestParam String password) {
        return userRemote.register(username, password);
    }
}
