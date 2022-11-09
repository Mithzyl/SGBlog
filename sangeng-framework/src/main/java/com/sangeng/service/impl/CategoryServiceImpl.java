package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Article;
import com.sangeng.domain.entity.Category;
import com.sangeng.domain.vo.CategoryVO;
import com.sangeng.mapper.CategoryMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.service.CategoryService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-11-09 23:37:25
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private ArticleService articleService;
    @Override
    public ResponseResult getCategoryList() {
        /**
         * ①要求只展示有发布正式文章的分类 ②必须是正常状态的分类
         */

        // 查询发布正式文章的分类
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper();
        articleLambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleLambdaQueryWrapper);

        // 获取分类id并去重
        Set<Long> categoryIds = articleList.stream().
                map(article -> article.getCategoryId()).
                collect(Collectors.toSet());

        // 查询正常状态的分类
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream().filter(category -> category.getStatus().equals(SystemConstants.STATUS_NORMAL))
                .collect(Collectors.toList());

        // 返回Vo对象
        List<CategoryVO> categoryVOList = BeanCopyUtils.copyBeanList(categories, CategoryVO.class);

        return ResponseResult.okResult(categoryVOList);
    }
}
