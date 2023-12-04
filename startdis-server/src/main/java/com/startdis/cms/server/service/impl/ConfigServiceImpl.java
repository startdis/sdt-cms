package com.startdis.cms.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.startdis.cms.server.service.ConfigService;
import com.startdis.cms.domain.model.entity.Config;
import com.startdis.cms.infra.mapper.ConfigMapper;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 全局配置(Config)服务实现层
 */
@Service("configService")
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

}

