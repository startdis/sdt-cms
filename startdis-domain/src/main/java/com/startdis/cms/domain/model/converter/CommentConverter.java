package com.startdis.cms.domain.model.converter;


import com.startdis.cms.domain.model.dto.post.CommentPostDTO;
import com.startdis.cms.domain.model.dto.put.CommentPutDTO;
import com.startdis.cms.domain.model.entity.Comment;
import com.startdis.cms.domain.model.query.CommentQuery;
import com.startdis.cms.domain.model.vo.CommentVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 评论留言 CommentConverter转换接口
 */
@Mapper
public interface CommentConverter {

    CommentConverter INSTANT = Mappers.getMapper(CommentConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "groupTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Comment postDtoToEntity(CommentPostDTO commentDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "groupTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Comment putDtoToEntity(CommentPutDTO commentDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "groupTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Comment queryToEntity(CommentQuery commentQuery);

    CommentVO entityToVO(Comment comment);

}
