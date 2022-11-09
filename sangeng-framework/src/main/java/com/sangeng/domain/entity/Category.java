package com.sangeng.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_category")
public class Category {
    @TableId
    private Long id;

    // 分类名
    private String name;

    // 父分类id 没有父分类为-1
    private Long pid;

    // 描述
    private String description;

    // 状态 0: 正常 1:禁用
    private String status;

    // 删除标志 0: 未删除 1: 已删除
    private int delFlag;

    private Long createBy;

    // 创建时间
    private Date createTime;

    private Long updateBy;
    
    // 修改时间
    private Date updateTime;
}
