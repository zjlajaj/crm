package com.zjl.service.strategy.custom;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjl.bo.custom.CustomBo;
import com.zjl.pojo.Custom;

import java.util.List;

public interface CustomQueryHandlerStrategy {
    IPage<CustomBo> VagueQuery(Integer pageNum,String keyword);
}
