package com.world.producer.controller;

import com.world.producer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : lkz
 * @Description : 用户Controller
 * @CreateTime : 2017/07/24
 */
@RestController
@RequestMapping("web/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public String register(@RequestParam String username, @RequestParam String password) {
//        int i = userService.checkUserRegister(new User(username));
//        if (i == 1) {
//            return "该用户已注册";
//        }
        userService.register(username, password);
        return username + password;
    }
}
