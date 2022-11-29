package com.sangeng.domain.vo;

import com.sangeng.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentListVo {

    private Long id;

    // 文章id
    private Long articleId;

    // 根评论id
    private Long rootId;

    // 评论内容
    private String content;

    // 所回复的目标评论userid
    private Long toCommentUserid;
    private String toCommentUserName;

    // 回复目标评论id
    private Long toCommentId;

    private Long createBy;

    private Date createTime;

    private String userName;

    // 子评论列表
    private List<Comment> children;


}
