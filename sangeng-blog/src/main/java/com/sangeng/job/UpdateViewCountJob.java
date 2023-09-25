package com.sangeng.job;

import com.sangeng.domain.entity.Article;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.utils.RedisCache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateViewCountJob {
    @Resource
    private RedisCache redisCache;

    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleMapper articleMapper;

    // 间隔一段时间更新redis中浏览量到数据库中
    @Scheduled(cron = "0/30 * * * * ?")
    public void updateViewCount(){
        // 获取redis中的浏览量

        Map<String, Integer> viewCount = redisCache.getCacheMap("article:viewCount");

        // 获取所有文章的最新浏览量
        // entrySet 入口
        List<Article> articleList = viewCount.entrySet().
                stream().map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        // 更新到数据库中
        articleService.updateBatchById(articleList);
    }
}

//@Component
//public class UpdateViewCountJob {
//
//    @Resource
//    private RedisCache redisCache;
//
//    @Resource
//    private ArticleService articleService;
//
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void updateViewCount(){
//        //获取redis中的浏览量
//        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
//
//        List<Article> articles = viewCountMap.entrySet()
//                .stream()
//                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
//                .collect(Collectors.toList());
//        //更新到数据库中
//        articleService.updateBatchById(articles);
//
//    }
//}
