package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SgLinkVo {

    // id
    private Long id;

    // 描述
    private String description;

    // 地址url
    private String address;

    // logo (pic url)
    private String logo;

    // name
    private String name;
}
