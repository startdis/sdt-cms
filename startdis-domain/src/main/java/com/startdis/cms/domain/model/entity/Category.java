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
 * @desc 分类管理 CategoryDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("cms_category")
public class Category extends BaseEntity {


    /**
     * 父级分类
     */
    private String parentId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类别名
     */
    private String alias;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 分类排序
     */
    private Long sort;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
