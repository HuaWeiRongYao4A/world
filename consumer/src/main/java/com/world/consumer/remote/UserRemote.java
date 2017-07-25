package com.world.consumer.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @CreateTime : 2017/07/24
 */
@FeignClient(name = "world-producer")
public interface UserRemote {
    @PostMapping(value = "web/user/register")
    String register(@RequestParam("username") String username, @RequestParam("password") String password);
}
