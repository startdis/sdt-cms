package com.startdis.cms.server.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.startdis.cms.domain.model.dto.post.ArticleTagsPostDTO;
import com.startdis.cms.domain.model.dto.put.ArticlePutDTO;
import com.startdis.cms.domain.model.entity.Article;
import com.startdis.cms.domain.model.entity.ArticleTags;
import com.startdis.cms.domain.model.entity.Tag;
import com.startdis.cms.domain.model.vo.TagVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文章内容(Article)服务定义层
 */
public interface ArticleService extends IService<Article> {
    List<TagVO> builderTags(String id);

    void saveArticleTags(List<ArticleTagsPostDTO> articleTags, String articleId);

    void deleteArticleTag(String articleId);
}
