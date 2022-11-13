package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.SgLink;
import com.sangeng.domain.vo.SgLinkVo;
import com.sangeng.mapper.SgLinkMapper;
import com.sangeng.service.SgLinkService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 友链(SgLink)表服务实现类
 *
 * @author makejava
 * @since 2022-11-12 22:32:23
 */
@Service("sgLinkService")
public class SgLinkServiceImpl extends ServiceImpl<SgLinkMapper, SgLink> implements SgLinkService {

    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<SgLink> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 审核通过
        lambdaQueryWrapper.eq(SgLink::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        // 未删除
        lambdaQueryWrapper.eq(SgLink::getDelFlag, SystemConstants.ARTICLE_STATUS_NORMAL);

        List<SgLink> sgLinks = list(lambdaQueryWrapper);

        List<SgLinkVo> sgLinkVos = BeanCopyUtils.copyBeanList(sgLinks, SgLinkVo.class);

        return ResponseResult.okResult(sgLinkVos);
    }
}
