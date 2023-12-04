package com.startdis.cms.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.startdis.cms.domain.model.converter.ArticleConverter;
import com.startdis.cms.domain.model.converter.ArticleTagsConverter;
import com.startdis.cms.domain.model.dto.post.ArticleTagsPostDTO;
import com.startdis.cms.domain.model.entity.Article;
import com.startdis.cms.domain.model.entity.ArticleTags;
import com.startdis.cms.domain.model.entity.FileInfo;
import com.startdis.cms.domain.model.entity.Tag;
import com.startdis.cms.domain.model.vo.ArticleVO;
import com.startdis.cms.domain.model.vo.TagVO;
import com.startdis.cms.infra.mapper.ArticleMapper;
import com.startdis.cms.server.service.ArticleService;
import com.startdis.cms.server.service.ArticleTagsService;
import com.startdis.cms.server.service.FileInfoService;
import com.startdis.cms.server.service.TagService;
import com.startdis.comm.core.constant.Constants;
import com.startdis.comm.domain.bean.ResultBean;
import com.startdis.comm.exception.custom.BusinessException;
import com.startdis.comm.util.bean.BeanCopyKits;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
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
    @Resource
    private FileInfoService fileInfoService;

    /**
     * 组装文章标签
     *
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
     *
     * @param articleTags 文章标签
     * @param articleId   文章ID
     */
    @Override
    public void saveArticleTags(List<ArticleTagsPostDTO> articleTags, String articleId) {
        for (ArticleTagsPostDTO articleTagDTO : articleTags) {
            ArticleTags articleTag = ArticleTagsConverter.INSTANT.postDtoToEntity(articleTagDTO);
            articleTag.setArticleId(articleId);
            articleTag.setStatus(1);
            articleTagsService.save(articleTag);
        }
    }

    /**
     * 移除文章标签
     *
     * @param articleId 文章ID
     */
    @Override
    public void deleteArticleTag(String articleId) {
        Map<String, Object> params = new HashMap<>();
        params.put("articleId", articleId);
        articleTagsService.removeByMap(params);
    }

    @Override
    public ArticleVO getByInfoId(String id) {
        Article article = this.getById(id);
        //处理格式转换
        ArticleVO articleVO = ArticleConverter.INSTANT.entityToVO(article);
        //组装文章标签
        List<TagVO> tagVOS = this.builderTags(id);
        articleVO.setArticleTags(tagVOS);
        //组装图片信息
        if (StringUtils.isNotEmpty(articleVO.getCoverImage())) {
            List<FileInfo> fileInfos = initFileInfo(articleVO.getCoverImage());
            articleVO.setCoverImageList(fileInfos);
        }
        return articleVO;
    }

    private List<FileInfo> initFileInfo(String fileIds) {
        List<String> fileIdList = Arrays.asList(fileIds.split(",")).stream().map(s -> String.format(s.trim())).collect(Collectors.toList());
        List<FileInfo> fileInfoVOList = new ArrayList<>();
        fileIdList.stream().forEach(f -> {
            if (ObjectUtils.isNotEmpty(fileInfoService.getFileById(f))) {
                String json = JSON.toJSONString(fileInfoService.getFileById(f));
                JSONObject jsonObject = JSONObject.parseObject(json);
                FileInfo fileInfo = JSON.parseObject(jsonObject.getString("data"), FileInfo.class);
                if (ObjectUtils.isNotEmpty(fileInfo)) {
                    ResultBean resultBean = null;
                    try {
                        resultBean = fileInfoService.share(fileInfo.getFileUrl(), 300000);
                    } catch (Exception e) {
                        throw new BusinessException(Constants.SUCCESS, "获取封面图信息异常！");
                    }
                    JSONObject urlobj = JSON.parseObject(JSON.toJSONString(resultBean));
                    fileInfo.setImageCheckUrl(urlobj.getString("data"));
                }
                fileInfoVOList.add(fileInfo);
            }
        });
        return fileInfoVOList;
    }
}

