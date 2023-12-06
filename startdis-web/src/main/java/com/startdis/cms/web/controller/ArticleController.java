package com.startdis.cms.web.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.startdis.cms.domain.model.converter.ArticleConverter;
import com.startdis.cms.domain.model.dto.post.ArticlePostDTO;
import com.startdis.cms.domain.model.dto.put.ArticlePutDTO;
import com.startdis.cms.domain.model.entity.Article;
import com.startdis.cms.domain.model.query.ArticleQuery;
import com.startdis.cms.domain.model.vo.ArticleVO;
import com.startdis.cms.domain.model.vo.TagVO;
import com.startdis.cms.server.service.ArticleService;
import com.startdis.cms.server.service.ArticleTagsService;
import com.startdis.comm.domain.bean.PagerBean;
import com.startdis.comm.domain.bean.ResultBean;
import com.startdis.comm.domain.model.PageQuery;
import com.startdis.comm.util.bean.BeanCopyKits;
import com.startdis.comm.util.id.SnowflakeIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文章内容(Article)控制层
 */
@Validated
@RestController
@ApiSort(value = 1)
@Api(value = "Article", tags = {"文章内容"})
@RequestMapping("article")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleTagsService articleTagsService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation("查询单条")
    public ResultBean<ArticleVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        ArticleVO articleVO = articleService.getByInfoId(id);
        return ResultBean.success(articleVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param articleQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @ApiOperation("查询所有")
    public ResultBean<List<ArticleVO>> list(@RequestBody ArticleQuery articleQuery) {
        //处理格式转换
        Article article = ArticleConverter.INSTANT.queryToEntity(articleQuery);
        //执行分页查询
        List<Article> listResult = articleService.list(new QueryWrapper<>(article));
        List<ArticleVO> articleVOS = BeanCopyKits.coverList(listResult, ArticleVO.class);
        //组装文章标签
        for (ArticleVO articleVO : articleVOS) {
            List<TagVO> tagVOS = articleService.builderTags(articleVO.getId());
            articleVO.setArticleTags(tagVOS);
        }
        return ResultBean.success(articleVOS);
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery    分页对象
     * @param articleQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @ApiOperation("分页查询")
    public ResultBean<PagerBean<ArticleVO>> page(PageQuery pageQuery, ArticleQuery articleQuery) {
        //处理分页条件
        Page<Article> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Article article = ArticleConverter.INSTANT.queryToEntity(articleQuery);
        //执行分页查询
        Page<Article> pageResult = articleService.page(page, new QueryWrapper<>(article));
        List<ArticleVO> articleVOS = BeanCopyKits.coverList(pageResult.getRecords(), ArticleVO.class);
        //组装文章标签
        for (ArticleVO articleVO : articleVOS) {
            List<TagVO> tagVOS = articleService.builderTags(articleVO.getId());
            articleVO.setArticleTags(tagVOS);
        }
        PagerBean<ArticleVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), articleVOS);
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param articleDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation("新增数据")
    @Transactional(rollbackFor = Exception.class,transactionManager = "transactionManager")
    public ResultBean<Map<String, String>> insert(@RequestBody @Validated ArticlePostDTO articleDTO) {
        //处理格式转换
        Article article = ArticleConverter.INSTANT.postDtoToEntity(articleDTO);
        //执行数据保存
        String id = SnowflakeIDUtils.getInstance().nextIdStr();
        article.setId(id);
        articleService.save(article);
        if (CollectionUtil.isNotEmpty(articleDTO.getArticleTags())){
            //保存文章标签
            articleService.saveArticleTags(articleDTO.getArticleTags(), id);
        }
        Map<String, String> map = new HashMap<>();
        map.put("articleId", id);
        return ResultBean.success(map);
    }

    /**
     * 修改数据
     *
     * @param articleDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @ApiOperation("修改数据")
    @Transactional(rollbackFor = Exception.class,transactionManager = "transactionManager")
    public ResultBean<Map<String, String>> update(@RequestBody @Validated ArticlePutDTO articleDTO) {
        //处理格式转换
        Article article = ArticleConverter.INSTANT.putDtoToEntity(articleDTO);
        //执行数据更新
        articleService.updateById(article);
        //保存文章标签
        articleService.deleteArticleTag(articleDTO.getId());
        if (CollectionUtil.isNotEmpty(articleDTO.getArticleTags())){
            //保存文章标签
            articleService.saveArticleTags(articleDTO.getArticleTags(), articleDTO.getId());
        }
        Map<String, String> map = new HashMap<>();
        map.put("articleId", article.getId());
        return ResultBean.success(map);
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除数据")
    @Transactional(rollbackFor = Exception.class,transactionManager = "transactionManager")
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        for (String id : ids) {
            articleService.removeById(id);
            articleService.deleteArticleTag(id);
        }
        return ResultBean.success("删除文章成功");
    }

}

