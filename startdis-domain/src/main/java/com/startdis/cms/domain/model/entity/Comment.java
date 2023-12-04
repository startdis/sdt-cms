package com.startdis.cms.domain.model.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.startdis.comm.domain.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 评论留言 CommentDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("cms_comment")
public class Comment extends BaseEntity {


    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 评论人名称
     */
    private String commentator;

    /**
     * 评论人电话
     */
    private String phone;

    /**
     * 评论人邮箱
     */
    private String email;

    /**
     * 评论时的IP
     */
    private String ip;

    /**
     * 评论人的网站
     */
    private String website;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 是否审核（1评论 2留言）
     */
    private Integer type;

    /**
     * 是否审核（0未审 1审核）
     */
    private Integer status;


}
