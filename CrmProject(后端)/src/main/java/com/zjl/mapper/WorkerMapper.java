package com.zjl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjl.bo.admin.WorkerBo;
import com.zjl.bo.statistic.Info;
import com.zjl.pojo.Worker;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 12278
* @description 针对表【worker】的数据库操作Mapper
* @createDate 2022-12-08 18:30:13
* @Entity com.zjl.pojo.Worker
*/
public interface WorkerMapper extends BaseMapper<Worker> {
    IPage<WorkerBo> selectWorkerBo(Page<WorkerBo> page);


}




