package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {

    private Long id;

    // 分类id
    private Long categoryId;

    //分类名
    private String categoryName;

    // 标题
    private String title;

    // 内容
    private String content;

    // 是否允许评论
    private String is_comment;

    // 浏览量
    private Long viewCount;

    private Date createTime;


}
