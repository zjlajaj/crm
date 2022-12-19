package com.zjl.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjl.bo.stock.StockBo;
import com.zjl.pojo.Stock;
import com.zjl.query.stock.AddStockQuery;
import com.zjl.query.stock.UpdateStockQuery;
import com.zjl.result.ResultBean;
import com.zjl.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/stock")
@Transactional
@Api(tags = "库存管理接口")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/addStock")
    @ApiOperation("添加库存")
    public ResultBean<Void> addStock(@RequestBody @Valid AddStockQuery addStockQuery){
        ResultBean<Void> resultBean = new ResultBean<>();
        Stock stock=new Stock();
        stock.setStockUnit(addStockQuery.getStockUnit());
        stock.setStockName(addStockQuery.getStockName());
        stock.setStockPrice(addStockQuery.getStockPrice());
        stock.setStockOut(addStockQuery.getStockOut());
        stock.setStockLeft(addStockQuery.getStockLeft());
        boolean save = stockService.save(stock);
        if (save){
            resultBean.setCode(HttpStatus.OK.value())
                    .setMsg("添加成功");
        }
        else {
            resultBean.setMsg("未知原因添加失败")
                    .setCode(HttpStatus.BAD_REQUEST.value());
        }
        return resultBean.setTimestamp(System.currentTimeMillis());
    }

    @PutMapping("updateStock")
    @ApiOperation("更新库存")
    public ResultBean<Void> updateStock(@RequestBody @Valid UpdateStockQuery updateStockQuery){
        ResultBean<Void> resultBean = new ResultBean<>();
        Stock stock=new Stock();
        stock.setPkId(updateStockQuery.getId());
        stock.setStockUnit(updateStockQuery.getStockUnit());
        stock.setStockName(updateStockQuery.getStockName());
        stock.setStockPrice(updateStockQuery.getStockPrice());
        stock.setStockOut(updateStockQuery.getStockOut());
        stock.setStockLeft(updateStockQuery.getStockLeft());
        boolean update = stockService.updateById(stock);
        if (update){
            resultBean.setCode(HttpStatus.OK.value())
                    .setMsg("更新成功");
        }
        else {
            resultBean.setMsg("未知原因更新失败")
                    .setCode(HttpStatus.BAD_REQUEST.value());
        }
        return resultBean.setTimestamp(System.currentTimeMillis());
    }

    @GetMapping("/queryStock/{pageNum}")
    @ApiOperation("查询库存")
    public ResultBean<IPage<StockBo>> queryStock(@PathVariable("pageNum") Integer pageNum){
        return stockService.queryAll(pageNum);
    }
}
