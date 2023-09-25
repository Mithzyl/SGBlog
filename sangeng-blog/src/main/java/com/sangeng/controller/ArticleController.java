package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Article;
import com.sangeng.service.ArticleService;
import org.springframework.web.bind.annotation.*;

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

    // 文章列表
    @GetMapping("/articleList")
    public ResponseResult articleList(@RequestParam(name = "pageNum") int pageNum,
                                      @RequestParam(name = "pageSize") int pageSize,
                                      @RequestParam(name = "categoryId") Long categoryId){

        ResponseResult responseResult = articleService.articleList(pageNum, pageSize, categoryId);

        return responseResult;
    }

    // 文章详情
    // 1. 查询文章具体内容
    // 2. 更新文章浏览量 (另一个方法 低耦合)
    @GetMapping("/{id}")
    public ResponseResult articleDetail(@PathVariable("id") Long id){
        ResponseResult responseResult = articleService.articleDetail(id);

        return responseResult;
    }

    // 更新浏览量
    @PutMapping("/updateViewCount/{id}")
    @CrossOrigin
    public ResponseResult updateViewCount(@PathVariable("id")Long id){
        return articleService.updateViewCount(id);
    }
}
