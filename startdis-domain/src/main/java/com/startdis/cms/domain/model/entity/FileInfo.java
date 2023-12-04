package com.startdis.cms.domain.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.startdis.comm.domain.model.BaseEntity;
import com.startdis.comm.domain.model.RecipientExcelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Startdis
 * @email startdis@dianjiu.cc
 * @desc 上传的文件信息表 FileInfoDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("cms_file_info")
public class FileInfo extends BaseEntity {


    /**
     * 文件上传名称
     */
    private String fileName;

    /**
     * 文件上传地址
     */
    private String fileUrl;

    /**
     * 上传文件大小
     */
    private String fileSize;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;
    @TableField(exist = false)
    private  String   imageCheckUrl;
    @TableField(exist = false)
    private List<RecipientExcelDto> recipientExcelDtos;
}
