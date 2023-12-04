package com.startdis.cms.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.startdis.cms.domain.model.dto.post.ArticleTagsPostDTO;
import com.startdis.cms.domain.model.entity.Article;
import com.startdis.cms.domain.model.vo.ArticleVO;
import com.startdis.cms.domain.model.vo.TagVO;

import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文章内容(Article)服务定义层
 */
public interface ArticleService extends IService<Article> {
    List<TagVO> builderTags(String id);

    void saveArticleTags(List<ArticleTagsPostDTO> articleTags, String articleId);

    void deleteArticleTag(String articleId);

    ArticleVO getByInfoId(String id);
}
