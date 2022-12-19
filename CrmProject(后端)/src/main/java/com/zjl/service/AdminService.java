package com.zjl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjl.bo.admin.WorkerBo;
import com.zjl.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjl.result.ResultBean;

/**
* @author 12278
* @description 针对表【admin】的数据库操作Service
* @createDate 2022-12-11 22:53:07
*/
public interface AdminService extends IService<Admin> {

    ResultBean<IPage<WorkerBo>> queryAll(Integer pageNum);
}
