package com.startdis.cms.domain.model.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.startdis.comm.domain.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文章标签表 ArticleTagsDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("cms_article_tags")
public class ArticleTags extends BaseEntity {


    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 标签ID
     */
    private String tagId;

    /**
     * 是否审核（0未审 1审核）
     */
    private Integer status;


}
