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
 * @desc 文章内容 ArticleDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("cms_article")
public class Article extends BaseEntity {


    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章副标题
     */
    private String subTitle;

    /**
     * 自定义路径
     */
    private String customUrl;

    /**
     * 文章封面图
     */
    private String coverImage;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章分类ID
     */
    private String categoryId;

    /**
     * 文章分类名称
     */
    private String categoryName;

    /**
     * 文章类型（1原创 2转载 3翻译）
     */
    private Integer type;

    /**
     * 开启评论（0关闭 1开启）
     */
    private Integer comment;

    /**
     * 是否启用（0草稿 1发布）
     */
    private Integer status;


    /**
     * 阅读量
     */
    private Long views;


}
