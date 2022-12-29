package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Comment;
import com.sangeng.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 查询某篇文章评论
     * @param articleId 文章id
     * @param pageNum 分页页码
     * @param pageSize 分页大小
     * @return
     */
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, int pageNum, int pageSize){
        return commentService.commentList(articleId, pageNum, pageSize);
    }

    /**
     * 发表评论
     * @param comment
     * @return
     */
    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){

        return commentService.addComment(comment);
    }
}
