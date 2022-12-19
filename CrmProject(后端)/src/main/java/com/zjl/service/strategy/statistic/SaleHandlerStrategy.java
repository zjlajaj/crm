package com.zjl.service.strategy.statistic;

import com.zjl.bo.statistic.Info;
import com.zjl.mapper.DealMapper;
import com.zjl.mapper.StockMapper;
import com.zjl.utils.SpringUtils;

import java.util.List;

public class SaleHandlerStrategy implements InfoHandlerStrategy {
    private StockMapper stockMapper= SpringUtils.getBean("stockMapper");
    @Override
    public List<Info> getInfo() {
        return stockMapper.selectSaleInfo();
    }
}
