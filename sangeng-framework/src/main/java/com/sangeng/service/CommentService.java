package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-11-29 15:18:42
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String type, Long articleId, int pageNum, int pageSize);

    ResponseResult addComment(Comment comment);

    ResponseResult linkCommentList(Long linkId, int pageNum, int pageSize);
}


