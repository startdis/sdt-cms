package com.startdis.cms.domain.model.converter;


import com.startdis.cms.domain.model.dto.post.ArticleTagsPostDTO;
import com.startdis.cms.domain.model.dto.put.ArticleTagsPutDTO;
import com.startdis.cms.domain.model.entity.ArticleTags;
import com.startdis.cms.domain.model.query.ArticleTagsQuery;
import com.startdis.cms.domain.model.vo.ArticleTagsVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文章标签表 ArticleTagsConverter转换接口
 */
@Mapper
public interface ArticleTagsConverter {

    ArticleTagsConverter INSTANT = Mappers.getMapper(ArticleTagsConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "groupTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ArticleTags postDtoToEntity(ArticleTagsPostDTO articleTagsDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "groupTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ArticleTags putDtoToEntity(ArticleTagsPutDTO articleTagsDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "groupTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ArticleTags queryToEntity(ArticleTagsQuery articleTagsQuery);

    ArticleTagsVO entityToVO(ArticleTags articleTags);

}
