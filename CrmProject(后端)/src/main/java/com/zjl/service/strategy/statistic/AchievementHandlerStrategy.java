package com.zjl.service.strategy.statistic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjl.bo.custom.CustomBo;
import com.zjl.bo.statistic.Info;
import com.zjl.mapper.CustomMapper;
import com.zjl.mapper.DealMapper;
import com.zjl.service.strategy.custom.CustomQueryHandlerStrategy;
import com.zjl.utils.SpringUtils;

import java.util.List;

public class AchievementHandlerStrategy implements InfoHandlerStrategy {
    private DealMapper dealMapper=SpringUtils.getBean("dealMapper");
    @Override
    public List<Info> getInfo() {
        List<Info> infos = dealMapper.selectAchievement();
        return infos;
    }
}
