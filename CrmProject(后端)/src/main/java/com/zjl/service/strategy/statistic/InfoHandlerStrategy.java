package com.zjl.service.strategy.statistic;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjl.bo.custom.CustomBo;
import com.zjl.bo.statistic.Info;

import java.util.List;

public interface InfoHandlerStrategy {
    List<Info> getInfo();
}
