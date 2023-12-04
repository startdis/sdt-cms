package com.startdis.cms.domain.model.query;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文章标签表 ArticleTagsQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@ApiModel(description = "文章标签表")
public class ArticleTagsQuery implements Serializable {
    private static final long serialVersionUID = -71289294393783186L;
    /**
     * ID
     */
    @ApiModelProperty("ID")
    private String id;
    /**
     * 文章ID
     */
    @ApiModelProperty("文章ID")
    private String articleId;
    /**
     * 标签ID
     */
    @ApiModelProperty("标签ID")
    private String tagId;
    /**
     * 是否审核（0未审 1审核）
     */
    @ApiModelProperty("是否审核（0未审 1审核）")
    private Integer status;

}

