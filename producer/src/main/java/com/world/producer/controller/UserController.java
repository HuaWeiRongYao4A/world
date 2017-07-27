package com.world.producer.controller;

import com.world.producer.entity.User;
import com.world.producer.service.UserService;
import com.world.producer.util.JavaWebTokenUtil;
import com.world.producer.viewobject.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("login")
    public UserLoginVO login(@RequestParam String username, @RequestParam String password) {
        User user = userService.detail(new User(username, password));
        UserLoginVO userLoginVO = new UserLoginVO();
        if (user == null) {
            userLoginVO.setCode("-1");
            userLoginVO.setMsg("用户名或密码错误");
            return userLoginVO;
        }
        if (user.getIsActive() != 1) {
            userLoginVO.setCode("-1");
            userLoginVO.setMsg("用户暂未激活，请先通过邮箱激活！");
            return userLoginVO;
        }
        //把用户登录信息放进token
        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put("id", user.getId());
        String token = JavaWebTokenUtil.createJavaWebToken(loginInfo);
        System.out.println("token: " + token);
        userLoginVO.setToken(token);
        userLoginVO.setUsername(user.getUsername());
        userLoginVO.setCode("1");
        return userLoginVO;
    }

}

