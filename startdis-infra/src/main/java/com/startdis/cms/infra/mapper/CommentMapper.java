package com.startdis.cms.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.startdis.cms.domain.model.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文章评论(Comment)数据层
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
