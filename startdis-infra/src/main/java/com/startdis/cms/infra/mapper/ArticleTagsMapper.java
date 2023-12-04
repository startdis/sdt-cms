package com.startdis.cms.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.startdis.cms.domain.model.entity.ArticleTags;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文章标签表(ArticleTags)数据层
 */
@Mapper
public interface ArticleTagsMapper extends BaseMapper<ArticleTags> {

}
