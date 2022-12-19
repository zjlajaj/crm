package com.zjl.service.strategy.statistic;

import com.zjl.bo.statistic.Info;
import com.zjl.mapper.CustomMapper;
import com.zjl.utils.SpringUtils;

import java.util.List;

public class IndustryHandlerStrategy implements InfoHandlerStrategy {
    private CustomMapper customMapper= SpringUtils.getBean("customMapper");
    @Override
    public List<Info> getInfo() {
        return customMapper.selectIndustryInfo();
    }
}
