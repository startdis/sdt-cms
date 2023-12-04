package com.startdis.cms.domain.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.startdis.cms.domain.model.entity.FileInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文章内容 ArticleVO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@ApiModel(description = "文章内容")
public class ArticleVO implements Serializable {
    private static final long serialVersionUID = 712769432768315748L;
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
     * 文章标签集合
     */
    @ApiModelProperty("文章标签集合")
    private List<TagVO> articleTags;
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
     * 是否删除（0正常 1删除）
     */
    @ApiModelProperty("是否删除（0正常 1删除）")
    private Integer deleted;
    /**
     * 阅读量
     */
    @ApiModelProperty("阅读量")
    private Long views;
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
    /**
     * 文章封面图文件对象
     */
    @ApiModelProperty("文章封面图文件对象")
    private List<FileInfo> coverImageList;
}
