package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.SysUser;
import org.apache.catalina.User;

public interface BlogLoginService extends IService<SysUser> {


    ResponseResult login(User user);

    ResponseResult logout();
}
