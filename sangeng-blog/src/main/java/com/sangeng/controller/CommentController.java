package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, int pageNum, int pageSize){
        return commentService.commentList(articleId, pageNum, pageSize);
    }
}
