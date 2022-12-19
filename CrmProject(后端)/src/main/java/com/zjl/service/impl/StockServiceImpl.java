package com.zjl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjl.bo.stock.StockBo;
import com.zjl.pojo.Stock;
import com.zjl.result.ResultBean;
import com.zjl.service.StockService;
import com.zjl.mapper.StockMapper;
import com.zjl.system.SystemConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
* @author 12278
* @description 针对表【stock】的数据库操作Service实现
* @createDate 2022-12-08 18:30:09
*/
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock>
    implements StockService{

    @Autowired
    private StockMapper stockMapper;
    @Override
    public ResultBean<IPage<StockBo>> queryAll(Integer pageNum) {
        ResultBean<IPage<StockBo>> resultBean = new ResultBean<>();
        Page<StockBo> page=new Page<>(pageNum, SystemConst.PAGE_SIZE);
        IPage<StockBo> stockBoIPage = stockMapper.selectStockBo(page);
        return resultBean.setTimestamp(System.currentTimeMillis())
                        .setData(stockBoIPage)
                        .setMsg("查询成功")
                        .setCode(HttpStatus.OK.value());
    }
}




