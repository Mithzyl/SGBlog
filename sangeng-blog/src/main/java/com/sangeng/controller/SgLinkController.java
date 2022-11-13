package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.service.SgLinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/link")
public class SgLinkController {

    @Resource
    private SgLinkService sgLinkService;

    //在友链页面要查询出所有的审核通过的友链。
    @GetMapping("/getAllLink")
    public ResponseResult getAllLink(){
        ResponseResult responseResult = sgLinkService.getAllLink();

        return responseResult;
    }

}
