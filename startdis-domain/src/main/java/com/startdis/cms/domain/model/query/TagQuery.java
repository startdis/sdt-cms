package com.startdis.cms.domain.model.query;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 标签管理 TagQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@ApiModel(description = "标签管理")
public class TagQuery implements Serializable {
    private static final long serialVersionUID = -11249697950882143L;
    /**
     * ID
     */
    @ApiModelProperty("ID")
    private String id;
    /**
     * 标签名称
     */
    @ApiModelProperty("标签名称")
    private String name;
    /**
     * 是否启用（0停用 1启用）
     */
    @ApiModelProperty("是否启用（0停用 1启用）")
    private Integer status;

}

