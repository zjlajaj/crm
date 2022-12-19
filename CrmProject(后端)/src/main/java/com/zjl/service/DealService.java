package com.zjl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjl.bo.deal.DealBo;
import com.zjl.pojo.Deal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjl.result.ResultBean;

import java.util.List;

/**
* @author 12278
* @description 针对表【deal】的数据库操作Service
* @createDate 2022-12-08 18:30:02
*/
public interface DealService extends IService<Deal> {

    ResultBean<IPage<DealBo>> queryAll(Integer pageNum);

    List<DealBo> getList();
}
