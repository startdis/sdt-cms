<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">SDT CMS</h1>
<h4 align="center">SDT 即 Startdis Dev Tools 启迪快速开发平台</h4>
<h4 align="center">SDT CMS是基于启迪（Startdis）快速开发平台搭建的内容管理系统。</h4>

## 平台简介

SDT CMS致力于开发最精简、实用的CMS管理系统，适合搭建个人博客、企业官网等。 基于[Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)协议，毫无保留给个人及企业免费使用，由[江苏点九科技有限公司](https://dianjiu.cc)提供技术支持。

## 相关链接
### 项目源码
Gitee：https://gitee.com/startdis/sdt-cms

Github：https://github.com/startdis/sdt-cms  

Gitlab：https://gitlab.com/startdis/sdt-cms

### 关于启迪
启迪平台：https://startdis.com  

项目文档：https://startdis.com/docs/cms  

体验地址：https://cms.startdis.top  
- startdis/startdis2022

## 系统模块

~~~
startdis-boot     
├── docker                                        // 容器部署
├── docs                                          // 说明文档
│   └── drawio                                    // 流程图设计
│   └── pdman                                     // 数据库设计
│   └── sqls                                      // 脚本模块
├── startdis-app                                  // H5、小程序、App等C端门面接口模块 [18082]
├── startdis-domain                               // 领域实体模块，包含实体、值对象、聚合、事件等
├── startdis-infra                                // 领域基础设施模块，包含持久化
├── startdis-gen                                  // 基础数据代码生成模块
├── startdis-job                                  // 分布式任务实现模块
├── startdis-server                               // 业务服务实现模块
├── startdis-web                                  // B端门户接口模块 [18081]
├── startdis-vue                                  // B端门户前端模块 [8081]
├── pom.xml                                       // 公共依赖
~~~

