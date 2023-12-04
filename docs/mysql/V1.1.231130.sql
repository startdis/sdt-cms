-- 新增文章标签中间表
DROP TABLE IF EXISTS cms_article_tags;
CREATE TABLE cms_article_tags(
                                 id VARCHAR(19) NOT NULL   COMMENT 'ID' ,
                                 article_id VARCHAR(19)    COMMENT '文章ID' ,
                                 tag_id VARCHAR(19)    COMMENT '标签ID' ,
                                 status INT2    COMMENT '是否审核（0未审 1审核）' ,
                                 deleted INT2    COMMENT '是否删除（0正常 1删除）' ,
                                 revision INT8    COMMENT '乐观锁' ,
                                 group_tenant_id VARCHAR(19)    COMMENT '集团租户ID' ,
                                 company_tenant_id VARCHAR(19)    COMMENT '公司租户ID' ,
                                 created_by VARCHAR(64)    COMMENT '创建人' ,
                                 created_at TIMESTAMP    COMMENT '创建时间' ,
                                 updated_by VARCHAR(64)    COMMENT '更新人' ,
                                 updated_at TIMESTAMP    COMMENT '更新时间' ,
                                 PRIMARY KEY (id)
)  COMMENT = '文章标签表';