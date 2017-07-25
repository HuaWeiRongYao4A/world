package com.world.producer.controller;

import com.world.producer.entity.User;
import com.world.producer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        int i = userService.checkUserRegister(new User(username));
        if (i == 1) {
            return "该用户已注册";
        }
        userService.register(username, password);
        return username + password;
    }

    @GetMapping("active")
    public String active(@RequestParam String activeCode) {
        User user = new User();
        user.setActiveCode(activeCode);
        user.setIsActive((byte) 1);
        //查询用户状态
        if (userService.detail(user).getIsActive() == 1) return "已激活，不需要重复激活";
        userService.active(user);
        return "success";
    }
}
