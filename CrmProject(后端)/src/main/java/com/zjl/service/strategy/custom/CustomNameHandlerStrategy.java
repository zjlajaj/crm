package com.zjl.service.strategy.custom;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjl.bo.custom.CustomBo;
import com.zjl.mapper.CustomMapper;
import com.zjl.system.SystemConst;
import com.zjl.utils.SpringUtils;

public class CustomNameHandlerStrategy implements CustomQueryHandlerStrategy {
    private CustomMapper customMapper= SpringUtils.getBean("customMapper");
    @Override
    public IPage<CustomBo> VagueQuery(Integer pageNum,String keyword) {
        QueryWrapper<CustomBo> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("custom_name",keyword);
        Page<CustomBo> page=new Page<>(pageNum, SystemConst.PAGE_SIZE);
        IPage<CustomBo> page1 = customMapper.vagueCustomBo(page,queryWrapper);
        return page1;
    }
}
