package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Article;
import com.sangeng.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/list")
    public List<Article> test(){
        return articleService.list();

    }

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        ResponseResult responseResult = articleService.hotArticleList();

        return responseResult;
    }

    @GetMapping("/articleList")
    public ResponseResult articleList(@RequestParam(name = "pageNum") int pageNum,
                                      @RequestParam(name = "pageSize") int pageSize,
                                      @RequestParam(name = "categoryId") Long categoryId){

        ResponseResult responseResult = articleService.articleList(pageNum, pageSize, categoryId);

        return responseResult;
    }
}
