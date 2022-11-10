package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Article;
import com.sangeng.domain.vo.HotArticleVO;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{
    @Override
    public ResponseResult hotArticleList() {
        /**
         * 需要查询浏览量最高的前10篇文章的信息。要求展示文章标题和浏览量。把能让用户自己点击跳转到具体的文章详情进行浏览。
         *
         * ​	注意：不能把草稿展示出来，不能把删除了的文章查询出来。要按照浏览量进行降序排序。
         */
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper();
        // 展示未删除文章
        articleQueryWrapper.eq(Article::getDelFlag, SystemConstants.ARTICLE_STATUS_NORMAL); // 0:未删除 1:已删除

        // 不展示草稿
        articleQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL); // "0": 已发布 "1": 草稿

        // 按浏览量排序
        articleQueryWrapper.orderByDesc(Article::getViewCount);

        // 分页展示10条
        // 1. 创建分页对象
        Page<Article> page = new Page(1, 10);
        page(page, articleQueryWrapper);

        // 将page记录存入List集合中
        List<Article> articleList = page.getRecords();

        // 转换为HotArticleVo进行返回
        List<HotArticleVO> hotArticleVOList = BeanCopyUtils.copyBeanList(articleList, HotArticleVO.class);

        return ResponseResult.okResult(hotArticleVOList);
    }

    @Override
    public ResponseResult articleList(int pageNum, int pageSize, Long categoryId) {
        // 首页：查询所有的文章, 分类: 查询分类下的文章
        //①只能查询正式发布的文章 ②置顶的文章要显示在最前面

        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper();

        // categoryId >0时 表示在特定分类下查询文章
        articleLambdaQueryWrapper.eq(ObjectUtils.isNotNull(categoryId) && categoryId>0,
                                    Article::getCategoryId, categoryId);

        // 正式发布的文章
        articleLambdaQueryWrapper.eq(Article::getDelFlag, SystemConstants.ARTICLE_STATUS_NORMAL);
        articleLambdaQueryWrapper.eq(Article::getStatus, SystemConstants.STATUS_NORMAL);
        // 置顶文章倒序
        articleLambdaQueryWrapper.orderByDesc(Article::getIsTop);

        Page<Article> articlePage = new Page<>(pageNum, pageSize);
        page(articlePage, articleLambdaQueryWrapper);

        List<Article> article = articlePage.getRecords();

        // TODO: Create VO class to return certain value
        return ResponseResult.okResult(article);
    }
}
