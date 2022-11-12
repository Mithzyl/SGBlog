package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(int pageNum, int pageSize, Long categoryId);

    ResponseResult articleDetail(Long id);

    ResponseResult updateViewCount(Long id);
}
