DROP TABLE IF EXISTS cms_article;
CREATE TABLE cms_article(
    id VARCHAR(19) NOT NULL   COMMENT 'ID' ,
    title VARCHAR(128)    COMMENT '文章标题' ,
    sub_title VARCHAR(255)    COMMENT '文章副标题' ,
    custom_url VARCHAR(255)    COMMENT '自定义路径' ,
    cover_image VARCHAR(255)    COMMENT '文章封面图' ,
    content TEXT    COMMENT '文章内容' ,
    category VARCHAR(128)    COMMENT '文章分类' ,
    tags VARCHAR(255)    COMMENT '文章标签' ,
    views INT8    COMMENT '阅读量' ,
    comment INT2    COMMENT '开启评论（0关闭 1开启）' ,
    status INT2    COMMENT '是否启用（0草稿 1发布）' ,
    deleted INT2    COMMENT '是否删除（0正常 1删除）' ,
    revision INT8    COMMENT '乐观锁' ,
    group_tenant_id VARCHAR(19)    COMMENT '集团租户ID' ,
    company_tenant_id VARCHAR(19)    COMMENT '公司租户ID' ,
    created_by VARCHAR(64)    COMMENT '创建人' ,
    created_at TIMESTAMP    COMMENT '创建时间' ,
    updated_by VARCHAR(64)    COMMENT '更新人' ,
    updated_at TIMESTAMP    COMMENT '更新时间' ,
    PRIMARY KEY (id)
)  COMMENT = '文章内容';

DROP TABLE IF EXISTS cms_category;
CREATE TABLE cms_category(
    id VARCHAR(19) NOT NULL   COMMENT 'ID' ,
    parent_id VARCHAR(19)    COMMENT '父级分类' ,
    name VARCHAR(128)    COMMENT '分类名称' ,
    icon VARCHAR(255)    COMMENT '分类图标' ,
    sort INT8    COMMENT '分类排序' ,
    status INT2    COMMENT '是否启用（0停用 1启用）' ,
    deleted INT2    COMMENT '是否删除（0正常 1删除）' ,
    revision INT8    COMMENT '乐观锁' ,
    group_tenant_id VARCHAR(19)    COMMENT '集团租户ID' ,
    company_tenant_id VARCHAR(19)    COMMENT '公司租户ID' ,
    created_by VARCHAR(64)    COMMENT '创建人' ,
    created_at TIMESTAMP    COMMENT '创建时间' ,
    updated_by VARCHAR(64)    COMMENT '更新人' ,
    updated_at TIMESTAMP    COMMENT '更新时间' ,
    PRIMARY KEY (id)
)  COMMENT = '分类管理';

DROP TABLE IF EXISTS cms_tag;
CREATE TABLE cms_tag(
    id VARCHAR(19) NOT NULL   COMMENT 'ID' ,
    name VARCHAR(128)    COMMENT '标签名称' ,
    status INT2    COMMENT '是否启用（0停用 1启用）' ,
    deleted INT2    COMMENT '是否删除（0正常 1删除）' ,
    revision INT8    COMMENT '乐观锁' ,
    group_tenant_id VARCHAR(19)    COMMENT '集团租户ID' ,
    company_tenant_id VARCHAR(19)    COMMENT '公司租户ID' ,
    created_by VARCHAR(64)    COMMENT '创建人' ,
    created_at TIMESTAMP    COMMENT '创建时间' ,
    updated_by VARCHAR(64)    COMMENT '更新人' ,
    updated_at TIMESTAMP    COMMENT '更新时间' ,
    PRIMARY KEY (id)
)  COMMENT = '标签管理';

DROP TABLE IF EXISTS cms_comment;
CREATE TABLE cms_comment(
    id VARCHAR(19) NOT NULL   COMMENT 'ID' ,
    parent_id VARCHAR(19)    COMMENT '父级ID' ,
    article_id VARCHAR(19)    COMMENT '文章ID' ,
    commentator VARCHAR(64)    COMMENT '评论人名称' ,
    phone VARCHAR(128)    COMMENT '评论人电话' ,
    email VARCHAR(128)    COMMENT '评论人邮箱' ,
    ip VARCHAR(19)    COMMENT '评论时的IP' ,
    website VARCHAR(19)    COMMENT '评论人的网站' ,
    content VARCHAR(19)    COMMENT '评论内容' ,
    reply VARCHAR(19)    COMMENT '回复内容' ,
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
)  COMMENT = '文章评论';

DROP TABLE IF EXISTS cms_config;
CREATE TABLE cms_config(
    id VARCHAR(19) NOT NULL   COMMENT 'ID' ,
    code VARCHAR(64)    COMMENT '配置项编码' ,
    name VARCHAR(128)    COMMENT '配置项名称' ,
    value VARCHAR(255)    COMMENT '配置项的值' ,
    status INT2    COMMENT '是否启用（0停用 1启用）' ,
    deleted INT2    COMMENT '是否删除（0正常 1删除）' ,
    revision INT8    COMMENT '乐观锁' ,
    group_tenant_id VARCHAR(19)    COMMENT '集团租户ID' ,
    company_tenant_id VARCHAR(19)    COMMENT '公司租户ID' ,
    created_by VARCHAR(64)    COMMENT '创建人' ,
    created_at TIMESTAMP    COMMENT '创建时间' ,
    updated_by VARCHAR(64)    COMMENT '更新人' ,
    updated_at TIMESTAMP    COMMENT '更新时间' ,
    PRIMARY KEY (id)
)  COMMENT = '全局配置';

DROP TABLE IF EXISTS cms_link;
CREATE TABLE cms_link(
    id VARCHAR(19) NOT NULL   COMMENT 'ID' ,
    name VARCHAR(19)    COMMENT '网站名称' ,
    url VARCHAR(19)    COMMENT '网站链接' ,
    description VARCHAR(19)    COMMENT '网站描述' ,
    sort INT8    COMMENT '网站排序' ,
    type INT2    COMMENT '链接类型（0停用 1启用）' ,
    status INT2    COMMENT '是否启用（0停用 1启用）' ,
    deleted INT2    COMMENT '是否删除（0正常 1删除）' ,
    revision INT8    COMMENT '乐观锁' ,
    group_tenant_id VARCHAR(19)    COMMENT '集团租户ID' ,
    company_tenant_id VARCHAR(19)    COMMENT '公司租户ID' ,
    created_by VARCHAR(64)    COMMENT '创建人' ,
    created_at TIMESTAMP    COMMENT '创建时间' ,
    updated_by VARCHAR(64)    COMMENT '更新人' ,
    updated_at TIMESTAMP    COMMENT '更新时间' ,
    PRIMARY KEY (id)
)  COMMENT = '友情链接';

