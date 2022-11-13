package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.SgLink;


/**
 * 友链(SgLink)表服务接口
 *
 * @author makejava
 * @since 2022-11-12 22:32:23
 */
public interface SgLinkService extends IService<SgLink> {

    ResponseResult getAllLink();
}


