package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.LoginUser;
import com.sangeng.domain.entity.User;
import com.sangeng.domain.vo.BlogUserLoginVo;
import com.sangeng.domain.vo.UserInfoVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.mapper.SysUserMapper;
import com.sangeng.service.BlogLoginService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.utils.JwtUtil;
import com.sangeng.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BlogLoginServiceImpl extends ServiceImpl<SysUserMapper, User> implements BlogLoginService {

    @Resource
    private RedisCache redisCache;

    @Resource
    private AuthenticationManager authenticationManager;
    @Override
    public ResponseResult login(org.apache.catalina.User user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // 判断是否认证通过
        if (ObjectUtils.isNotNull(authenticate)){
            //  根据用户名获取jwt
            LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
            String userId = loginUser.getUser().getId().toString();

            String jwt = JwtUtil.createJWT(userId);

            //  用户信息存入redis
            redisCache.setCacheObject("bloglogin:"+userId, loginUser);

            // 封装token 和 userinfo进行返回
            UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);

            BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, userInfoVo);

            return ResponseResult.okResult(blogUserLoginVo);

        }else{
            throw new RuntimeException("用户名或密码错误");
        }

    }

    @Override
    public ResponseResult logout() {

        // jwt解析??
        // 从SecurityContext中获取用户信息和userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();

        // 获取redis信息
        String redisKey = "bloglogin:" + userId;
        Object cacheObject = redisCache.getCacheObject(redisKey);
        if(ObjectUtils.isNotNull(cacheObject)){
            // redis中存在用户信息
            // 删除redis中的信息
            redisCache.deleteObject(redisKey);

        }
        SecurityContextHolder.clearContext();

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMessage());

    }
}
