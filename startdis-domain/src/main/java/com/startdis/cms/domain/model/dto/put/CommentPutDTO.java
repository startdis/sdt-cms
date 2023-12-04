package com.startdis.cms.domain.model.dto.put;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 评论留言 CommentDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@ApiModel(description = "评论留言")
public class CommentPutDTO implements Serializable {
    private static final long serialVersionUID = -64959761621497262L;

    /**
     * ID
     */
    @ApiModelProperty("ID")
    private String id;

    /**
     * 父级ID
     */
    @ApiModelProperty("父级ID")
    private String parentId;

    /**
     * 文章ID
     */
    @ApiModelProperty("文章ID")
    private String articleId;

    /**
     * 评论人名称
     */
    @ApiModelProperty("评论人名称")
    private String commentator;

    /**
     * 评论人电话
     */
    @ApiModelProperty("评论人电话")
    private String phone;

    /**
     * 评论人邮箱
     */
    @ApiModelProperty("评论人邮箱")
    private String email;

    /**
     * 评论时的IP
     */
    @ApiModelProperty("评论时的IP")
    private String ip;

    /**
     * 评论人的网站
     */
    @ApiModelProperty("评论人的网站")
    private String website;

    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    private String content;

    /**
     * 是否审核（1评论 2留言）
     */
    @ApiModelProperty("是否审核（1评论 2留言）")
    private Integer type;

    /**
     * 是否审核（0未审 1审核）
     */
    @ApiModelProperty("是否审核（0未审 1审核）")
    private Integer status;


}
