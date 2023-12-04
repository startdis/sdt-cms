package com.startdis.cms.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.startdis.cms.domain.model.entity.FileInfo;
import com.startdis.comm.domain.bean.ResultBean;


/**
 * @author Startdis
 * @email startdis@dianjiu.cc
 * @desc 上传的文件信息表(FileInfo)服务定义层
 */
public interface FileInfoService extends IService<FileInfo> {

    ResultBean fileList(String fileName);

    ResultBean getFileById(String id);

    ResultBean share(String objectName, Integer expires) throws Exception;
}
