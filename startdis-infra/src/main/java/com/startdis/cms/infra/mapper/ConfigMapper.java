package com.startdis.cms.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.startdis.cms.domain.model.entity.Config;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 全局配置(Config)数据层
 */
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {

}
