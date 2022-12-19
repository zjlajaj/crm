package com.zjl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjl.bo.custom.CustomBo;
import com.zjl.bo.deal.DealBo;
import com.zjl.bo.statistic.Info;
import com.zjl.pojo.Deal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 12278
* @description 针对表【deal】的数据库操作Mapper
* @createDate 2022-12-08 18:30:02
* @Entity com.zjl.pojo.Deal
*/
public interface DealMapper extends BaseMapper<Deal> {
    IPage<DealBo> selectDealBo(Page<DealBo> page);

    List<DealBo> selectAll();

    List<Info> selectAchievement();

}




