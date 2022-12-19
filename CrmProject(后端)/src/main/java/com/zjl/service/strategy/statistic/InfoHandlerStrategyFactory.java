package com.zjl.service.strategy.statistic;

import com.zjl.service.strategy.custom.CustomAddrHandlerStrategy;
import com.zjl.service.strategy.custom.CustomNameHandlerStrategy;
import com.zjl.service.strategy.custom.CustomQueryHandlerStrategy;
import com.zjl.service.strategy.custom.CustomTelHandlerStrategy;

import java.util.HashMap;
import java.util.Map;

public class InfoHandlerStrategyFactory {
    private static final Map<String, InfoHandlerStrategy> map = new HashMap<>();
    static {
        map.put("achievement",new AchievementHandlerStrategy());
        map.put("from",new FromHandlerStrategy());
        map.put("industry",new IndustryHandlerStrategy());
        map.put("sale",new SaleHandlerStrategy());
    }
    public static InfoHandlerStrategy getStrategy(String type){
        return map.get(type);
    }
}
