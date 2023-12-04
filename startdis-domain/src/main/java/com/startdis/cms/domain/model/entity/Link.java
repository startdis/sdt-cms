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
 * @desc 友情链接 LinkDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("cms_link")
public class Link extends BaseEntity {


    /**
     * 网站名称
     */
    private String name;

    /**
     * 网站链接
     */
    private String url;

    /**
     * 网站描述
     */
    private String description;

    /**
     * 网站排序
     */
    private Long sort;

    /**
     * 链接类型（0停用 1启用）
     */
    private Integer type;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
