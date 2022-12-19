package com.zjl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjl.bo.admin.WorkerBo;
import com.zjl.bo.statistic.Info;
import com.zjl.bo.stock.StockBo;
import com.zjl.pojo.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 12278
* @description 针对表【stock】的数据库操作Mapper
* @createDate 2022-12-08 18:30:09
* @Entity com.zjl.pojo.Stock
*/
public interface StockMapper extends BaseMapper<Stock> {
    IPage<StockBo> selectStockBo(Page<StockBo> page);

    List<Info> selectSaleInfo();
}




