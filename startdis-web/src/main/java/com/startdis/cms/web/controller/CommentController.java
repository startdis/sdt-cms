package com.startdis.cms.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.startdis.cms.domain.model.converter.CommentConverter;
import com.startdis.cms.domain.model.dto.post.CommentPostDTO;
import com.startdis.cms.domain.model.dto.put.CommentPutDTO;
import com.startdis.cms.domain.model.entity.Comment;
import com.startdis.cms.domain.model.query.CommentQuery;
import com.startdis.cms.domain.model.vo.CommentVO;
import com.startdis.cms.server.service.CommentService;
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
 * @desc 评论留言(Comment)控制层
 */
@Validated
@RestController
@ApiSort(value = 1)
@Api(value = "Comment", tags = {"评论留言"})
@RequestMapping("comment")
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation("查询单条")
    public ResultBean<CommentVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        Comment comment = commentService.getById(id);
        //处理格式转换
        CommentVO commentVO = CommentConverter.INSTANT.entityToVO(comment);
        return ResultBean.success(commentVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param commentQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @ApiOperation("查询所有")
    public ResultBean<List<CommentVO>> list(@RequestBody CommentQuery commentQuery) {
        //处理格式转换
        Comment comment = CommentConverter.INSTANT.queryToEntity(commentQuery);
        //执行分页查询
        List<Comment> listResult = commentService.list(new QueryWrapper<>(comment));
        return ResultBean.success(BeanCopyKits.coverList(listResult, CommentVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery    分页对象
     * @param commentQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @ApiOperation("分页查询")
    public ResultBean<PagerBean<CommentVO>> page(PageQuery pageQuery, CommentQuery commentQuery) {
        //处理分页条件
        Page<Comment> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Comment comment = CommentConverter.INSTANT.queryToEntity(commentQuery);
        //执行分页查询
        Page<Comment> pageResult = commentService.page(page, new QueryWrapper<>(comment));
        PagerBean<CommentVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyKits.coverList(pageResult.getRecords(), CommentVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param commentDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation("新增数据")
    public ResultBean<Boolean> insert(@RequestBody @Validated CommentPostDTO commentDTO) {
        //处理格式转换
        Comment comment = CommentConverter.INSTANT.postDtoToEntity(commentDTO);
        //执行数据保存
        return ResultBean.success(commentService.save(comment));
    }

    /**
     * 修改数据
     *
     * @param commentDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @ApiOperation("修改数据")
    public ResultBean<Boolean> update(@RequestBody @Validated CommentPutDTO commentDTO) {
        //处理格式转换
        Comment comment = CommentConverter.INSTANT.putDtoToEntity(commentDTO);
        //执行数据更新
        return ResultBean.success(commentService.updateById(comment));
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
        return ResultBean.success(commentService.removeByIds(ids));
    }

}

