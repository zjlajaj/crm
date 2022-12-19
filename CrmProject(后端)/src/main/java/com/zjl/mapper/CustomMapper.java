package com.zjl.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjl.bo.admin.WorkerBo;
import com.zjl.bo.custom.CustomBo;
import com.zjl.bo.statistic.Info;
import com.zjl.pojo.Custom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 12278
* @description 针对表【custom】的数据库操作Mapper
* @createDate 2022-12-08 18:29:47
* @Entity com.zjl.pojo.Custom
*/
@Mapper
public interface CustomMapper extends BaseMapper<Custom> {
    IPage<CustomBo> selectCustomBo(Page<CustomBo> page);

    IPage<CustomBo> vagueCustomBo(Page<CustomBo> page, @Param(Constants.WRAPPER)QueryWrapper<CustomBo> queryWrapper);

    List<Info> selectFromInfo();

    List<Info> selectIndustryInfo();
}




