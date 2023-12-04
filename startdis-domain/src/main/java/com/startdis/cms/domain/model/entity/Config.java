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
 * @desc 全局配置 ConfigDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("cms_config")
public class Config extends BaseEntity {


    /**
     * 配置项类型（1用户信息 2网站配置 3邮件配置 4主题信息 5七牛配置）
     */
    private String type;

    /**
     * 配置项编码
     */
    private String code;

    /**
     * 配置项名称
     */
    private String name;

    /**
     * 配置项的值
     */
    private String value;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
