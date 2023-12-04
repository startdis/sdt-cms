package com.startdis.cms.web.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.startdis.cms.domain.model.converter.CategoryConverter;
import com.startdis.cms.domain.model.dto.post.CategoryPostDTO;
import com.startdis.cms.domain.model.dto.put.CategoryPutDTO;
import com.startdis.cms.domain.model.entity.Category;
import com.startdis.cms.domain.model.query.CategoryQuery;
import com.startdis.cms.domain.model.tree.CategoryTree;
import com.startdis.cms.domain.model.vo.CategoryVO;
import com.startdis.cms.server.service.CategoryService;
import com.startdis.comm.domain.bean.PagerBean;
import com.startdis.comm.domain.bean.ResultBean;
import com.startdis.comm.domain.bean.TreeBuild;
import com.startdis.comm.domain.model.PageQuery;
import com.startdis.comm.util.bean.BeanCopyKits;
import com.startdis.comm.util.object.ObjectKits;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 分类管理(Category)控制层
 */
@Validated
@RestController
@ApiSort(value = 1)
@Api(value = "Category", tags = {"分类管理"})
@RequestMapping("category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation("查询单条")
    public ResultBean<CategoryVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        Category category = categoryService.getById(id);
        //处理格式转换
        CategoryVO categoryVO = CategoryConverter.INSTANT.entityToVO(category);
        return ResultBean.success(categoryVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param categoryQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/tree")
    @ApiOperation("查询树结构")
    public ResultBean<List<CategoryTree>> tree(@RequestBody CategoryQuery categoryQuery) {
        // 根据名称进行模糊匹配
        LambdaQueryWrapper<Category> queryWrapper = Wrappers.<Category>lambdaQuery()
                .like(StringUtils.isNotEmpty(categoryQuery.getName()), Category::getName, categoryQuery.getName())
                .orderByAsc(Category::getSort);
        List<Category> listResult = categoryService.getBaseMapper().selectList(queryWrapper);
        // 原查询结果转换树形结构
        List<CategoryTree> menuTrees = BeanCopyKits.coverList(listResult, CategoryTree.class);

        //查询条件为空，默认查询根结点为0的一级部门
        if (ObjectKits.isEmpty(categoryQuery) && CollectionUtil.isNotEmpty(menuTrees)) {
            String rootId = "0";
            // 创建树形结构并返回
            TreeBuild treeBuild = new TreeBuild(rootId, menuTrees);
            return ResultBean.success(treeBuild.buildTree());
        }
        return ResultBean.success(menuTrees);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param categoryQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @ApiOperation("查询所有")
    public ResultBean<List<CategoryVO>> list(@RequestBody CategoryQuery categoryQuery) {
        //处理格式转换
        Category category = CategoryConverter.INSTANT.queryToEntity(categoryQuery);
        //执行分页查询
        List<Category> listResult = categoryService.list(new QueryWrapper<>(category));
        return ResultBean.success(BeanCopyKits.coverList(listResult, CategoryVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery     分页对象
     * @param categoryQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @ApiOperation("分页查询")
    public ResultBean<PagerBean<CategoryVO>> page(PageQuery pageQuery, CategoryQuery categoryQuery) {
        //处理分页条件
        Page<Category> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Category category = CategoryConverter.INSTANT.queryToEntity(categoryQuery);
        //执行分页查询
        Page<Category> pageResult = categoryService.page(page, new QueryWrapper<>(category));
        PagerBean<CategoryVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyKits.coverList(pageResult.getRecords(), CategoryVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param categoryDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation("新增数据")
    public ResultBean<Boolean> insert(@RequestBody @Validated CategoryPostDTO categoryDTO) {
        //处理格式转换
        Category category = CategoryConverter.INSTANT.postDtoToEntity(categoryDTO);
        //执行数据保存
        return ResultBean.success(categoryService.save(category));
    }

    /**
     * 修改数据
     *
     * @param categoryDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @ApiOperation("修改数据")
    public ResultBean<Boolean> update(@RequestBody @Validated CategoryPutDTO categoryDTO) {
        //处理格式转换
        Category category = CategoryConverter.INSTANT.putDtoToEntity(categoryDTO);
        //执行数据更新
        return ResultBean.success(categoryService.updateById(category));
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
        return ResultBean.success(categoryService.removeByIds(ids));
    }

}

