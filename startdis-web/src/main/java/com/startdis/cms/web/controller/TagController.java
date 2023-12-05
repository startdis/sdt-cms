package com.startdis.cms.web.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.startdis.cms.domain.model.converter.TagConverter;
import com.startdis.cms.domain.model.dto.post.TagPostDTO;
import com.startdis.cms.domain.model.dto.put.TagPutDTO;
import com.startdis.cms.domain.model.entity.Tag;
import com.startdis.cms.domain.model.query.TagQuery;
import com.startdis.cms.domain.model.vo.TagVO;
import com.startdis.cms.server.service.TagService;
import com.startdis.comm.core.constant.Constants;
import com.startdis.comm.domain.bean.PagerBean;
import com.startdis.comm.domain.bean.ResultBean;
import com.startdis.comm.domain.model.PageQuery;
import com.startdis.comm.exception.custom.BusinessException;
import com.startdis.comm.util.bean.BeanCopyKits;
import com.startdis.comm.util.id.SnowflakeIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @desc 标签管理(Tag)控制层
 */
@Validated
@RestController
@ApiSort(value = 1)
@Api(value = "Tag", tags = {"标签管理"})
@RequestMapping("tag")
public class TagController {
    /**
     * 服务对象
     */
    @Resource
    private TagService tagService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation("查询单条")
    public ResultBean<TagVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        Tag tag = tagService.getById(id);
        //处理格式转换
        TagVO tagVO = TagConverter.INSTANT.entityToVO(tag);
        return ResultBean.success(tagVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param tagQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @ApiOperation("查询所有")
    public ResultBean<List<TagVO>> list(@RequestBody TagQuery tagQuery) {
        //处理格式转换
        Tag tag = TagConverter.INSTANT.queryToEntity(tagQuery);
        //执行分页查询
        List<Tag> listResult = tagService.list(new QueryWrapper<>(tag));
        return ResultBean.success(BeanCopyKits.coverList(listResult, TagVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery 分页对象
     * @param tagQuery  查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @ApiOperation("分页查询")
    public ResultBean<PagerBean<TagVO>> page(PageQuery pageQuery, TagQuery tagQuery) {
        //处理分页条件
        Page<Tag> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Tag tag = TagConverter.INSTANT.queryToEntity(tagQuery);
        //执行分页查询
        Page<Tag> pageResult = tagService.page(page, new QueryWrapper<>(tag));
        PagerBean<TagVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyKits.coverList(pageResult.getRecords(), TagVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param tagDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation("新增数据")
    public ResultBean<Map<String, String>> insert(@RequestBody @Validated TagPostDTO tagDTO) {
        //处理格式转换
        Tag tag = TagConverter.INSTANT.postDtoToEntity(tagDTO);
        //执行数据保存
        checkExist(tag);
        String id = SnowflakeIDUtils.getInstance().nextIdStr();
        tag.setId(id);
        tagService.save(tag);
        Map<String, String> map = new HashMap<>();
        map.put("tagId", id);
        map.put("tagName", tagDTO.getName());
        return ResultBean.success(map);
    }

    /**
     * 修改数据
     *
     * @param tagDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @ApiOperation("修改数据")
    public ResultBean<Boolean> update(@RequestBody @Validated TagPutDTO tagDTO) {
        //处理格式转换
        Tag tag = TagConverter.INSTANT.putDtoToEntity(tagDTO);
        //执行数据更新
        checkExist(tag);
        return ResultBean.success(tagService.updateById(tag));
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
        return ResultBean.success(tagService.removeByIds(ids));
    }

    private void checkExist(Tag tag){
        LambdaQueryWrapper<Tag> wrapper = Wrappers.<Tag>lambdaQuery().eq(Tag::getName, tag.getName());
        if (StringUtils.isNotEmpty(tag.getId())){
            wrapper.ne(Tag::getId, tag.getId());
        }
        List<Tag> list = tagService.list(wrapper);
        if (CollectionUtil.isNotEmpty(list)){
            throw new BusinessException(Constants.ERROR, "标签名称已存在，请勿重复添加！");
        }
    }
}

