package com.startdis.cms.domain.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 评论留言 CommentVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@ApiModel(description = "评论留言")
public class CommentVO implements Serializable {
    private static final long serialVersionUID = -43921273784635020L;
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
    /**
     * 是否删除（0正常 1删除）
     */
    @ApiModelProperty("是否删除（0正常 1删除）")
    private Integer deleted;
    /**
     * 乐观锁
     */
    @ApiModelProperty("乐观锁")
    private Long revision;
    /**
     * 集团租户ID
     */
    @ApiModelProperty("集团租户ID")
    private String groupTenantId;
    /**
     * 公司租户ID
     */
    @ApiModelProperty("公司租户ID")
    private String companyTenantId;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createdBy;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    private String updatedBy;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    private LocalDateTime updatedAt;

}
