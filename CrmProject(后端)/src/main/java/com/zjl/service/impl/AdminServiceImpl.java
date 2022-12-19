package com.zjl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjl.bo.admin.WorkerBo;
import com.zjl.mapper.WorkerMapper;
import com.zjl.pojo.Admin;
import com.zjl.result.ResultBean;
import com.zjl.service.AdminService;
import com.zjl.mapper.AdminMapper;
import com.zjl.system.SystemConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
* @author 12278
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2022-12-11 22:53:07
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

    @Autowired
    private WorkerMapper workerMapper;
    @Override
    public ResultBean<IPage<WorkerBo>> queryAll(Integer pageNum) {
        ResultBean<IPage<WorkerBo>> resultBean = new ResultBean<>();
        Page<WorkerBo> page=new Page<>(pageNum, SystemConst.PAGE_SIZE);
        IPage<WorkerBo> workerBoIPage = workerMapper.selectWorkerBo(page);
        return resultBean.setTimestamp(System.currentTimeMillis())
                        .setData(workerBoIPage)
                        .setMsg("查询成功")
                        .setCode(HttpStatus.OK.value());
    }
}




