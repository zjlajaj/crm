package com.zjl.service.strategy.statistic;

import com.zjl.bo.statistic.Info;
import com.zjl.mapper.CustomMapper;
import com.zjl.mapper.WorkerMapper;
import com.zjl.pojo.Worker;
import com.zjl.utils.SpringUtils;

import java.util.List;

public class FromHandlerStrategy implements InfoHandlerStrategy {

    private CustomMapper customMapper= SpringUtils.getBean("customMapper");
    @Override
    public List<Info> getInfo() {
        List<Info> infos = customMapper.selectFromInfo();
        return infos;
    }
}
