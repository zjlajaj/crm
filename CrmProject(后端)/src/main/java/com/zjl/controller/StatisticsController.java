package com.zjl.controller;


import com.zjl.bo.statistic.Info;
import com.zjl.result.ResultBean;
import com.zjl.service.strategy.statistic.InfoHandlerStrategy;
import com.zjl.service.strategy.statistic.InfoHandlerStrategyFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@Transactional
@Api(tags = "统计接口")
public class StatisticsController {

    @GetMapping("/getInfo/{type}")
    @ApiOperation("统计,客户所属行业统计(industry),客户来源信息统计(from),员工业绩统计(achievement),库存销售情况统计(sale)")
    public ResultBean<List<Info>> getInfo(@PathVariable("type") String type){
        ResultBean<List<Info>> resultBean = new ResultBean<>();
        InfoHandlerStrategy strategy = InfoHandlerStrategyFactory.getStrategy(type);
        if (strategy==null){
            return resultBean.setTimestamp(System.currentTimeMillis())
                            .setData(null)
                            .setMsg("统计类型不合法")
                            .setCode(HttpStatus.OK.value());
        }
        return resultBean.setTimestamp(System.currentTimeMillis())
                        .setData(strategy.getInfo())
                        .setMsg("统计成功")
                        .setCode(HttpStatus.OK.value());
    }
}
