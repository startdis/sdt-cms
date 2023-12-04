package com.startdis.cms.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.startdis.cms.server.service.TagService;
import com.startdis.cms.domain.model.entity.Tag;
import com.startdis.cms.infra.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 标签管理(Tag)服务实现层
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}

