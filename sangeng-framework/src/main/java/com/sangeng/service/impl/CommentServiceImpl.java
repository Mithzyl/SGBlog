package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Comment;
import com.sangeng.domain.vo.CommentListVo;
import com.sangeng.domain.vo.CommentVo;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.CommentMapper;
import com.sangeng.service.CommentService;
import com.sangeng.service.UserService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-11-29 15:18:42
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private UserService userService;

    @Override
    public ResponseResult commentList(String type, Long articleId, int pageNum, int pageSize) {
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 根据传入的类型判断查询的评论为友联还是文章
        // 根据是否传入了文章id判断查询的评论为友联还是文章
        lambdaQueryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(type), Comment::getArticleId, articleId);

        // 查询评论类型
        lambdaQueryWrapper.eq(Comment::getType, type);
        // 查询为根评论的评论
        lambdaQueryWrapper.eq(Comment::getRootId, SystemConstants.ROOT_COMMENT);

        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);

        // 转换为CommentListVo
        List<CommentVo> listVos = toCommentVoList(page.getRecords());

        // 查询所有跟评论对应的子评论集合, 并赋值属性
        for(CommentVo commentVo : listVos){
            // 查询对应的子评论
            List<CommentVo> childrenList =  getChildren(commentVo.getId());

            commentVo.setChildren(childrenList);
        }
         //listVos.stream().map()


        return ResponseResult.okResult(new PageVo(listVos, page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        // 内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult linkCommentList(Long linkId, int pageNum, int pageSize) {
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getType, SystemConstants.LINK_COMMENT);

        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);

        List<CommentVo> commentVos = toCommentVoList(page.getRecords());

//        for(CommentVo commentVo : commentVos){
//            List<CommentVo> children = getChildren(commentVo.getId());
//
//            commentVo.setChildren(children);
//        }

        commentVos.stream().forEach(commentVo -> {
            commentVo.setChildren(
                    getChildren(commentVo.getId())
            );
        });

        return ResponseResult.okResult(commentVos);
    }

    /**
     * 根据根评论id查询所有的子评论
     * @param id 根评论id
     * @return
     */
    private List<CommentVo> getChildren(Long id){
         LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
         lambdaQueryWrapper.eq(Comment::getRootId, id);
         lambdaQueryWrapper.orderByAsc(Comment::getCreateTime);

         List<Comment> comments = list(lambdaQueryWrapper);

         List<CommentVo> commentVos = toCommentVoList(comments);

         return commentVos;
    }

    private List<CommentVo> toCommentVoList(List<Comment> records) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(records, CommentVo.class);

        for(CommentVo commentVo : commentVos){
            // 通过createBy查询用户名称
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);

            // 通过toCommentUserId查询用户的昵称并赋值
            // 如果toCommentUserId不为-1才进行查询
            if(commentVo.getToCommentUserId() != SystemConstants.TO_COMMENT_USER_ID_NOT_EXIST){
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getUserName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }

        return commentVos;
    }
}
