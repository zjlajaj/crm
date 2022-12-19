package com.zjl.service.strategy.custom;

import java.util.HashMap;
import java.util.Map;

public class CustomQueryHandlerStrategyFactory {
    private static final Map<String,CustomQueryHandlerStrategy> map = new HashMap<>();
    static {
        map.put("addr",new CustomAddrHandlerStrategy());
        map.put("name",new CustomNameHandlerStrategy());
        map.put("tel",new CustomTelHandlerStrategy());
    }
    public static CustomQueryHandlerStrategy getStrategy(String type){
        return map.get(type);
    }
}
