package com.world.consumer.remote;

import com.world.consumer.viewobject.UserLoginVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @CreateTime : 2017/07/24
 */
@FeignClient(name = "world-producer")
public interface UserRemote {
    @PostMapping(value = "web/user/register")
    String register(@RequestParam("username") String username, @RequestParam("password") String password);

    @GetMapping(value = "web/user/active")
    String active(@RequestParam("activeCode") String activeCode);

    @PostMapping(value = "web/user/login")
    UserLoginVO login(@RequestParam("username") String username, @RequestParam("password") String password);

}
