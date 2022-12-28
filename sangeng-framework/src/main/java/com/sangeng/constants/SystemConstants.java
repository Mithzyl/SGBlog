package com.sangeng.constants;

public class SystemConstants {
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    /**
     *  文章是已删除状态
     */
    public static final int ARTICLE_STATUS_DELETED = 1;

    /**
     * 正常状态
     */
    public static final String STATUS_NORMAL = "0";

    /**
     * 置顶
     */
    public static final String STATUS_TOP = "1";

    /**
     * 友链状态为审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";

    /**
     * 评论类型为文章评论
     */
    public static final String ARTICLE_COMMENT = "0";

    /**
     * 评论类型为友联评论
     */
    public static final String LINK_COMMENT = "1";

    /**
     * 评论为根评论
     */
    public static final Long ROOT_COMMENT = Long.valueOf(-1);

    /**
     * 评论目标用户不存在
     */
    public static final Long TO_COMMENT_USER_ID_NOT_EXIST = Long.valueOf(-1);

}
