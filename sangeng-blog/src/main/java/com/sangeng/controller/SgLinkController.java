package com.sangeng.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangeng.entity.SgLink;
import com.sangeng.service.SgLinkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 友链(SgLink)表控制层
 *
 * @author makejava
 * @since 2022-11-12 22:31:10
 */
@RestController
@RequestMapping("sgLink")
public class SgLinkController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SgLinkService sgLinkService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param sgLink 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<SgLink> page, SgLink sgLink) {
        return success(this.sgLinkService.page(page, new QueryWrapper<>(sgLink)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.sgLinkService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sgLink 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody SgLink sgLink) {
        return success(this.sgLinkService.save(sgLink));
    }

    /**
     * 修改数据
     *
     * @param sgLink 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody SgLink sgLink) {
        return success(this.sgLinkService.updateById(sgLink));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.sgLinkService.removeByIds(idList));
    }
}

