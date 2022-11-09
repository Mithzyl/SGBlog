package com.sangeng.domain.vo;

import com.sangeng.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVO {

    private Long id;

    private String title;

    private Long viewCount;

}
