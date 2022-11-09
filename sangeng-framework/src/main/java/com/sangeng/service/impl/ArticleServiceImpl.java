package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
}
