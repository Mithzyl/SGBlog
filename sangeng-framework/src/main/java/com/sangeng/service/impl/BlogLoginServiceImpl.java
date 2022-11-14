package com.sangeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.SysUser;
import com.sangeng.mapper.SysUserMapper;
import com.sangeng.service.BlogLoginService;
import io.netty.util.internal.SystemPropertyUtil;
import org.apache.catalina.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BlogLoginServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements BlogLoginService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        return null;
    }
}
