package com.startdis.cms.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.startdis.cms.domain.model.entity.Link;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 友情链接(Link)数据层
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}
