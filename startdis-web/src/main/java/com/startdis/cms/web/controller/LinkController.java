package com.startdis.cms.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.startdis.cms.domain.model.converter.LinkConverter;
import com.startdis.cms.domain.model.dto.post.LinkPostDTO;
import com.startdis.cms.domain.model.dto.put.LinkPutDTO;
import com.startdis.cms.domain.model.entity.Link;
import com.startdis.cms.domain.model.query.LinkQuery;
import com.startdis.cms.domain.model.vo.LinkVO;
import com.startdis.cms.server.service.LinkService;
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
 * @desc 友情链接(Link)控制层
 */
@Validated
@RestController
@ApiSort(value = 1)
@Api(value = "Link", tags = {"友情链接"})
@RequestMapping("link")
public class LinkController {
    /**
     * 服务对象
     */
    @Resource
    private LinkService linkService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation("查询单条")
    public ResultBean<LinkVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        Link link = linkService.getById(id);
        //处理格式转换
        LinkVO linkVO = LinkConverter.INSTANT.entityToVO(link);
        return ResultBean.success(linkVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param linkQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @ApiOperation("查询所有")
    public ResultBean<List<LinkVO>> list(@RequestBody LinkQuery linkQuery) {
        //处理格式转换
        Link link = LinkConverter.INSTANT.queryToEntity(linkQuery);
        //执行分页查询
        List<Link> listResult = linkService.list(new QueryWrapper<>(link));
        return ResultBean.success(BeanCopyKits.coverList(listResult, LinkVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery 分页对象
     * @param linkQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @ApiOperation("分页查询")
    public ResultBean<PagerBean<LinkVO>> page(PageQuery pageQuery, LinkQuery linkQuery) {
        //处理分页条件
        Page<Link> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Link link = LinkConverter.INSTANT.queryToEntity(linkQuery);
        //执行分页查询
        Page<Link> pageResult = linkService.page(page, new QueryWrapper<>(link));
        PagerBean<LinkVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyKits.coverList(pageResult.getRecords(), LinkVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param linkDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation("新增数据")
    public ResultBean<Boolean> insert(@RequestBody @Validated LinkPostDTO linkDTO) {
        //处理格式转换
        Link link = LinkConverter.INSTANT.postDtoToEntity(linkDTO);
        //执行数据保存
        return ResultBean.success(linkService.save(link));
    }

    /**
     * 修改数据
     *
     * @param linkDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @ApiOperation("修改数据")
    public ResultBean<Boolean> update(@RequestBody @Validated LinkPutDTO linkDTO) {
        //处理格式转换
        Link link = LinkConverter.INSTANT.putDtoToEntity(linkDTO);
        //执行数据更新
        return ResultBean.success(linkService.updateById(link));
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
        return ResultBean.success(linkService.removeByIds(ids));
    }

}

