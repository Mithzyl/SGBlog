package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Comment;
import com.sangeng.domain.vo.CommentListVo;
import com.sangeng.mapper.CommentMapper;
import com.sangeng.service.CommentService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-11-29 15:18:42
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public ResponseResult commentList(Long articleId, int pageNum, int pageSize) {
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 查询类型为文章的评论
        // TODO: 替换0为Constants常量
        lambdaQueryWrapper.eq(Comment::getType, SystemConstants.ARTICLE_COMMENT);

        // 根据文章id查询评论
        lambdaQueryWrapper.eq(Comment::getArticleId, articleId);

        // 查询为根评论的评论
        // TODO: 替换0为常量
        lambdaQueryWrapper.eq(Comment::getRootId, SystemConstants.ROOT_COMMENT);

        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);
        System.out.println(page.getRecords());

        // 转换为CommentListVo
        List<CommentListVo> listVos = toCommentVoList(page.getRecords());


        return ResponseResult.okResult(page);
    }

    private List<CommentListVo> toCommentVoList(List<Comment> records) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(records, CommentVo.class);
    }
}
