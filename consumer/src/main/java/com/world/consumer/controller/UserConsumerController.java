package com.world.consumer.controller;


import com.world.consumer.remote.UserRemote;
import com.world.common.entity.User;
import com.world.common.util.AuthUtil;
import com.world.common.viewobject.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("active")
    public String active(@RequestParam String activeCode) {
        return userRemote.active(activeCode);
    }

    @PostMapping("login")
    public UserLoginVO login(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setId(2);
        System.out.println(user);
        return userRemote.login(username, password);
    }

    @PostMapping("logout")
    public String logout(HttpServletRequest request) {
        try {
            Long id = AuthUtil.getUserId(request);
            if (id > 0) return "success";
        } catch (Exception e) {
            e.getStackTrace();
        }
        return "error";
    }
}
