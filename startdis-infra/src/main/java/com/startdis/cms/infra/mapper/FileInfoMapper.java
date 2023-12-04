package com.startdis.cms.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.startdis.cms.domain.model.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Startdis
 * @email startdis@dianjiu.cc
 * @desc 上传的文件信息表(FileInfo)数据层
 */
@Mapper
public interface FileInfoMapper extends BaseMapper<FileInfo> {

}
