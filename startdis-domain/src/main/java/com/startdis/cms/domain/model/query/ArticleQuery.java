package com.startdis.cms.domain.model.query;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文章内容 ArticleQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@ApiModel(description = "文章内容")
public class ArticleQuery implements Serializable {
    private static final long serialVersionUID = 987603394680342385L;
    /**
     * ID
     */
    @ApiModelProperty("ID")
    private String id;
    /**
     * 文章标题
     */
    @ApiModelProperty("文章标题")
    private String title;
    /**
     * 文章副标题
     */
    @ApiModelProperty("文章副标题")
    private String subTitle;
    /**
     * 自定义路径
     */
    @ApiModelProperty("自定义路径")
    private String customUrl;
    /**
     * 文章封面图
     */
    @ApiModelProperty("文章封面图")
    private String coverImage;
    /**
     * 文章摘要
     */
    @ApiModelProperty("文章摘要")
    private String summary;
    /**
     * 文章内容
     */
    @ApiModelProperty("文章内容")
    private String content;
    /**
     * 文章分类ID
     */
    @ApiModelProperty("文章分类ID")
    private String categoryId;
    /**
     * 文章分类名称
     */
    @ApiModelProperty("文章分类名称")
    private String categoryName;
    /**
     * 文章类型（1原创 2转载 3翻译）
     */
    @ApiModelProperty("文章类型（1原创 2转载 3翻译）")
    private Integer type;
    /**
     * 开启评论（0关闭 1开启）
     */
    @ApiModelProperty("开启评论（0关闭 1开启）")
    private Integer comment;
    /**
     * 是否启用（0草稿 1发布）
     */
    @ApiModelProperty("是否启用（0草稿 1发布）")
    private Integer status;
    /**
     * 阅读量
     */
    @ApiModelProperty("阅读量")
    private Long views;

}

