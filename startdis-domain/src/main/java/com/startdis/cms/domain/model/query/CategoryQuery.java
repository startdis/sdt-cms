package com.startdis.cms.domain.model.query;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 分类管理 CategoryQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@ApiModel(description = "分类管理")
public class CategoryQuery implements Serializable {
    private static final long serialVersionUID = 826563500625570666L;
    /**
     * ID
     */
    @ApiModelProperty("ID")
    private String id;
    /**
     * 父级分类
     */
    @ApiModelProperty("父级分类")
    private String parentId;
    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    private String name;
    /**
     * 分类别名
     */
    @ApiModelProperty("分类别名")
    private String alias;
    /**
     * 分类图标
     */
    @ApiModelProperty("分类图标")
    private String icon;
    /**
     * 分类排序
     */
    @ApiModelProperty("分类排序")
    private Long sort;
    /**
     * 是否启用（0停用 1启用）
     */
    @ApiModelProperty("是否启用（0停用 1启用）")
    private Integer status;

}

