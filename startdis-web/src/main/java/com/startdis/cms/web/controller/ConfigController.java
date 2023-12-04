package com.startdis.cms.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.startdis.cms.domain.model.converter.ConfigConverter;
import com.startdis.cms.domain.model.dto.post.ConfigPostDTO;
import com.startdis.cms.domain.model.dto.put.ConfigPutDTO;
import com.startdis.cms.domain.model.entity.Config;
import com.startdis.cms.domain.model.query.ConfigQuery;
import com.startdis.cms.domain.model.vo.ConfigVO;
import com.startdis.cms.server.service.ConfigService;
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
import java.util.Map;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 全局配置(Config)控制层
 */
@Validated
@RestController
@ApiSort(value = 1)
@Api(value = "Config", tags = {"全局配置"})
@RequestMapping("config")
public class ConfigController {
    /**
     * 服务对象
     */
    @Resource
    private ConfigService configService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation("查询单条")
    public ResultBean<ConfigVO> get(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        Config config = configService.getById(id);
        //处理格式转换
        ConfigVO configVO = ConfigConverter.INSTANT.entityToVO(config);
        return ResultBean.success(configVO);
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param configQuery 实例对象
     * @return 对象列表
     */
    @PostMapping(value = "/list")
    @ApiOperation("查询所有")
    public ResultBean<List<ConfigVO>> list(@RequestBody ConfigQuery configQuery) {
        //处理格式转换
        Config config = ConfigConverter.INSTANT.queryToEntity(configQuery);
        //执行分页查询
        List<Config> listResult = configService.list(new QueryWrapper<>(config));
        return ResultBean.success(BeanCopyKits.coverList(listResult, ConfigVO.class));
    }

    /**
     * 分页查询所有数据
     *
     * @param pageQuery   分页对象
     * @param configQuery 查询实体
     * @return 分页对象
     */
    @PostMapping(value = "/page")
    @ApiOperation("分页查询")
    public ResultBean<PagerBean<ConfigVO>> page(PageQuery pageQuery, ConfigQuery configQuery) {
        //处理分页条件
        Page<Config> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //处理格式转换
        Config config = ConfigConverter.INSTANT.queryToEntity(configQuery);
        //执行分页查询
        Page<Config> pageResult = configService.page(page, new QueryWrapper<>(config));
        PagerBean<ConfigVO> pageBean = new PagerBean<>(pageResult.getTotal(), pageResult.getCurrent(),
                pageResult.getSize(), BeanCopyKits.coverList(pageResult.getRecords(), ConfigVO.class));
        return ResultBean.success(pageBean);
    }

    /**
     * 新增数据
     *
     * @param configDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation("新增数据")
    public ResultBean<Boolean> insert(@RequestBody @Validated ConfigPostDTO configDTO) {
        //处理格式转换
        Config config = ConfigConverter.INSTANT.postDtoToEntity(configDTO);
        //执行数据保存
        return ResultBean.success(configService.save(config));
    }

    /**
     * 修改数据
     *
     * @param configDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @ApiOperation("修改数据")
    public ResultBean<Boolean> update(@RequestBody @Validated ConfigPutDTO configDTO) {
        //处理格式转换
        Config config = ConfigConverter.INSTANT.putDtoToEntity(configDTO);
        //执行数据更新
        return ResultBean.success(configService.updateById(config));
    }

    /**
     * 修改数据
     *
     * @param configs 实体对象
     * @return 修改结果
     */
    @PutMapping("/updateBatch")
    @ApiOperation("批量修改配置")
    public ResultBean<Boolean> updateBatch(@RequestBody @Validated Map<String,String> configs) {
        for (String key : configs.keySet()) {
            // 更新全局配置
            UpdateWrapper<Config> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("code", key);
            updateWrapper.set("value", configs.get(key));
            configService.update(updateWrapper);
        }
        //执行数据更新
        return ResultBean.success("批量修改配置成功！");
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
        return ResultBean.success(configService.removeByIds(ids));
    }

}

