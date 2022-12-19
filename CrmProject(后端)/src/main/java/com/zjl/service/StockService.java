package com.zjl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjl.bo.stock.StockBo;
import com.zjl.pojo.Stock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjl.result.ResultBean;

/**
* @author 12278
* @description 针对表【stock】的数据库操作Service
* @createDate 2022-12-08 18:30:09
*/
public interface StockService extends IService<Stock> {

    ResultBean<IPage<StockBo>> queryAll(Integer pageNum);
}
