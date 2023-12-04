package com.startdis.cms.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.startdis.cms.server.service.ArticleTagsService;
import com.startdis.cms.domain.model.entity.ArticleTags;
import com.startdis.cms.infra.mapper.ArticleTagsMapper;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文章标签表(ArticleTags)服务实现层
 */
@Service("articleTagsService")
public class ArticleTagsServiceImpl extends ServiceImpl<ArticleTagsMapper, ArticleTags> implements ArticleTagsService {

}

