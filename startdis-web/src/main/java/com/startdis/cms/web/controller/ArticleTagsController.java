package com.startdis.cms.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.startdis.cms.domain.model.converter.ArticleTagsConverter;
import com.startdis.cms.domain.model.dto.post.ArticleTagsPostDTO;
import com.startdis.cms.domain.model.dto.put.ArticleTagsPutDTO;
import com.startdis.cms.domain.model.entity.ArticleTags;
import com.startdis.cms.domain.model.query.ArticleTagsQuery;
import com.startdis.cms.domain.model.vo.ArticleTagsVO;
import com.startdis.cms.server.service.ArticleTagsService;
import com.startdis.comm.domain.bean.PagerBean;
import com.startdis.comm.domain.bean.ResultBean;
import com.startdis.comm.domain.model.PageQuery;
import com.startdis.comm.util.bean.BeanCopyKits;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文章标签表(ArticleTags)控制层
 */
@Validated
@RestController
@ApiSort(value = 1)
@Api(value = "ArticleTags", tags = {"文章标签表"})
@RequestMapping("articleTags")
public class ArticleTagsController {
    /**
     * 服务对象
     */
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
    public ResultBean<ArticleTagsVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        ArticleTags articleTags = articleTagsService.getById(id);
        //处理格式转换
        ArticleTagsVO articleTagsVO = ArticleTagsConverter.INSTANT.entityToVO(articleTags);
        return ResultBean.success(articleTagsVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param articleTagsQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @ApiOperation("查询所有")
    public ResultBean<List<ArticleTagsVO>> list(@RequestBody ArticleTagsQuery articleTagsQuery) {
        //处理格式转换
        ArticleTags articleTags = ArticleTagsConverter.INSTANT.queryToEntity(articleTagsQuery);
        //执行分页查询
        List<ArticleTags> listResult = articleTagsService.list(new QueryWrapper<>(articleTags));
        return ResultBean.success(BeanCopyKits.coverList(listResult, ArticleTagsVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery        分页对象
     * @param articleTagsQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @ApiOperation("分页查询")
    public ResultBean<PagerBean<ArticleTagsVO>> page(PageQuery pageQuery, ArticleTagsQuery articleTagsQuery) {
        //处理分页条件
        Page<ArticleTags> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        ArticleTags articleTags = ArticleTagsConverter.INSTANT.queryToEntity(articleTagsQuery);
        //执行分页查询
        Page<ArticleTags> pageResult = articleTagsService.page(page, new QueryWrapper<>(articleTags));
        PagerBean<ArticleTagsVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyKits.coverList(pageResult.getRecords(), ArticleTagsVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param articleTagsDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation("新增数据")
    public ResultBean<Boolean> insert(@RequestBody @Validated ArticleTagsPostDTO articleTagsDTO) {
        //处理格式转换
        ArticleTags articleTags = ArticleTagsConverter.INSTANT.postDtoToEntity(articleTagsDTO);
        //执行数据保存
        return ResultBean.success(articleTagsService.save(articleTags));
    }

    /**
     * 修改数据
     *
     * @param articleTagsDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @ApiOperation("修改数据")
    public ResultBean<Boolean> update(@RequestBody @Validated ArticleTagsPutDTO articleTagsDTO) {
        //处理格式转换
        ArticleTags articleTags = ArticleTagsConverter.INSTANT.putDtoToEntity(articleTagsDTO);
        //执行数据更新
        return ResultBean.success(articleTagsService.updateById(articleTags));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除数据")
    public ResultBean<Boolean> delete(@RequestParam("ids") List<String> ids) {
        return ResultBean.success(articleTagsService.removeByIds(ids));
    }

}

