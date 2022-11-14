package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.mysql.cj.log.Log;
import com.sangeng.domain.entity.LoginUser;
import com.sangeng.domain.entity.SysUser;
import com.sangeng.mapper.SysUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // 根据用户名查询用户
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(SysUser::getUserName, s);
        SysUser sysUser = sysUserMapper.selectOne(lambdaQueryWrapper);

        // 判断是否查询到用户
        if(ObjectUtils.isNull(sysUser)){
            throw new RuntimeException("用户不存在");
        }

        LoginUser loginUser = new LoginUser(sysUser);

        // 返回用户信息
        // TODO: 查询权限信息封装

        return loginUser;
    }
}
