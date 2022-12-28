package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.User;

public interface BlogLoginService extends IService<User> {


    ResponseResult login(org.apache.catalina.User user);

    ResponseResult logout();
}
