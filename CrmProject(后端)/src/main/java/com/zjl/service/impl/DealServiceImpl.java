package com.zjl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjl.bo.deal.DealBo;
import com.zjl.pojo.Deal;
import com.zjl.result.ResultBean;
import com.zjl.service.DealService;
import com.zjl.mapper.DealMapper;
import com.zjl.system.SystemConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 12278
* @description 针对表【deal】的数据库操作Service实现
* @createDate 2022-12-08 18:30:02
*/
@Service
public class DealServiceImpl extends ServiceImpl<DealMapper, Deal>
    implements DealService{

    @Autowired
    private DealMapper dealMapper;

    @Override
    public ResultBean<IPage<DealBo>> queryAll(Integer pageNum) {
        ResultBean<IPage<DealBo>> resultBean = new ResultBean<>();
        Page<DealBo> page=new Page<>(pageNum, SystemConst.PAGE_SIZE);
        IPage<DealBo> dealBoIPage = dealMapper.selectDealBo(page);
        return resultBean.setTimestamp(System.currentTimeMillis())
                        .setData(dealBoIPage)
                        .setMsg("")
                        .setCode(HttpStatus.OK.value());
    }

    @Override
    public List<DealBo> getList() {
        List<DealBo> dealBos = dealMapper.selectAll();
        return dealBos;
    }
}




