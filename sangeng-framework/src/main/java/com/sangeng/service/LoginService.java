package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.User;

public interface LoginService extends IService<User> {


    ResponseResult login(User user);

    ResponseResult logout();
}
