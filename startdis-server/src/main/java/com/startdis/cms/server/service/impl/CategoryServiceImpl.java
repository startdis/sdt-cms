package com.startdis.cms.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.startdis.cms.server.service.CategoryService;
import com.startdis.cms.domain.model.entity.Category;
import com.startdis.cms.infra.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 分类管理(Category)服务实现层
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}

