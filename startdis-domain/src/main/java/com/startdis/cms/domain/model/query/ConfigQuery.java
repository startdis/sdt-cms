package com.startdis.cms.domain.model.query;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 全局配置 ConfigQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@ApiModel(description = "全局配置")
public class ConfigQuery implements Serializable {
    private static final long serialVersionUID = -60226254941367205L;
    /**
     * ID
     */
    @ApiModelProperty("ID")
    private String id;
    /**
     * 配置项类型（1用户信息 2网站配置 3邮件配置 4主题信息 5七牛配置）
     */
    @ApiModelProperty("配置项类型（1用户信息 2网站配置 3邮件配置 4主题信息 5七牛配置）")
    private String type;
    /**
     * 配置项编码
     */
    @ApiModelProperty("配置项编码")
    private String code;
    /**
     * 配置项名称
     */
    @ApiModelProperty("配置项名称")
    private String name;
    /**
     * 配置项的值
     */
    @ApiModelProperty("配置项的值")
    private String value;
    /**
     * 是否启用（0停用 1启用）
     */
    @ApiModelProperty("是否启用（0停用 1启用）")
    private Integer status;

}

