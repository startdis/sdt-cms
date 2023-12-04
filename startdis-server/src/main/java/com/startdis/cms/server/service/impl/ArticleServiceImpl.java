package com.startdis.cms.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.startdis.cms.domain.model.converter.ArticleTagsConverter;
import com.startdis.cms.domain.model.dto.post.ArticleTagsPostDTO;
import com.startdis.cms.domain.model.dto.put.ArticlePutDTO;
import com.startdis.cms.domain.model.entity.Article;
import com.startdis.cms.domain.model.entity.ArticleTags;
import com.startdis.cms.domain.model.entity.Tag;
import com.startdis.cms.domain.model.vo.TagVO;
import com.startdis.cms.infra.mapper.ArticleMapper;
import com.startdis.cms.server.service.ArticleService;
import com.startdis.cms.server.service.ArticleTagsService;
import com.startdis.cms.server.service.TagService;
import com.startdis.comm.util.bean.BeanCopyKits;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文章内容(Article)服务实现层
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    public TagService tagService;
    @Resource
    private ArticleTagsService articleTagsService;

    /**
     * 组装文章标签
     * @param id
     * @return
     */
    @Override
    public List<TagVO> builderTags(String id) {
        //查询中间表
        LambdaQueryWrapper<ArticleTags> lambdaQuery = Wrappers.lambdaQuery(ArticleTags.class);
        lambdaQuery.select(ArticleTags::getTagId);
        lambdaQuery.eq(ArticleTags::getArticleId, id);
        List<String> tagIds = articleTagsService.listObjs(lambdaQuery).stream().map(o -> (String) o).collect(Collectors.toList());
        //查询文章标签集合
        List<Tag> tags = tagService.listByIds(tagIds);
        return BeanCopyKits.coverList(tags, TagVO.class);
    }

    /**
     * 保存文章标签
     * @param articleTags 文章标签
     * @param articleId 文章ID
     */
    @Override
    public void saveArticleTags(List<ArticleTagsPostDTO> articleTags, String articleId) {
        for (ArticleTagsPostDTO articleTagDTO : articleTags) {
            ArticleTags articleTag = ArticleTagsConverter.INSTANT.postDtoToEntity(articleTagDTO);
            articleTag.setArticleId(articleId);
            articleTagsService.save(articleTag);
        }
    }

    /**
     * 移除文章标签
     * @param articleId 文章ID
     */
    @Override
    public void deleteArticleTag(String articleId) {
        Map<String, Object> params = new HashMap<>();
        params.put("articleId", articleId);
        articleTagsService.removeByMap(params);
    }

}

