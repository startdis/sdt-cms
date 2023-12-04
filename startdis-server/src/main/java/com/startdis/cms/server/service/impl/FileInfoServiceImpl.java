package com.startdis.cms.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.startdis.cms.domain.model.entity.FileInfo;
import com.startdis.cms.infra.mapper.FileInfoMapper;
import com.startdis.cms.server.service.FileInfoService;
import com.startdis.comm.domain.bean.ResultBean;
import com.startdis.comm.minio.service.MinIOService;
import com.startdis.comm.util.auth.AuthInfoUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Startdis
 * @email startdis@dianjiu.cc
 * @desc 上传的文件信息表(FileInfo)服务实现层
 */
@Service("fileInfoService")
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

    @Resource
    MinIOService minIOService;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Override
    public ResultBean fileList(String fileName) {
        List<FileInfo> fileInfos = this.baseMapper.selectList(Wrappers.<FileInfo>lambdaQuery()
                .like(StringUtils.isNotEmpty(fileName), FileInfo::getFileName, fileName));
        return ResultBean.success(fileInfos);
    }

    @Override
    public ResultBean getFileById(String id) {
        FileInfo fileInfo = super.getById(id);
        return ResultBean.success(fileInfo);
    }

    @Override
    public ResultBean share(String objectName, Integer expires) throws Exception{
        String groupTenantId = AuthInfoUtils.getGroupTenantId();
        String companyTenantId = AuthInfoUtils.getCompanyTenantId();
        return ResultBean.success("下载成功！",
                minIOService.getPresignedObjectUrl(bucketName, groupTenantId + "/" + companyTenantId + "/" + objectName, expires));
    }
}

