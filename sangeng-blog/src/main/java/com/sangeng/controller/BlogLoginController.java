package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.service.BlogLoginService;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BlogLoginController {

    @Resource
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        ResponseResult responseResult = blogLoginService.login(user);

        return responseResult;
    }
}
