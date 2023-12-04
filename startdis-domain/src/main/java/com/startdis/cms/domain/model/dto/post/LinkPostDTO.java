package com.startdis.cms.domain.model.dto.post;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 友情链接 LinkDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@ApiModel(description = "友情链接")
public class LinkPostDTO implements Serializable {
    private static final long serialVersionUID = -90638540571288750L;


    /**
     * 网站名称
     */
    @ApiModelProperty("网站名称")
    private String name;

    /**
     * 网站链接
     */
    @ApiModelProperty("网站链接")
    private String url;

    /**
     * 网站描述
     */
    @ApiModelProperty("网站描述")
    private String description;

    /**
     * 网站排序
     */
    @ApiModelProperty("网站排序")
    private Long sort;

    /**
     * 链接类型（0停用 1启用）
     */
    @ApiModelProperty("链接类型（0停用 1启用）")
    private Integer type;

    /**
     * 是否启用（0停用 1启用）
     */
    @ApiModelProperty("是否启用（0停用 1启用）")
    private Integer status;


}
