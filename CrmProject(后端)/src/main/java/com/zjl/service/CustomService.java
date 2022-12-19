package com.zjl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjl.bo.custom.CustomBo;
import com.zjl.pojo.Custom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjl.result.ResultBean;

/**
* @author 12278
* @description 针对表【custom】的数据库操作Service
* @createDate 2022-12-08 18:29:47
*/
public interface CustomService extends IService<Custom> {

    ResultBean<IPage<CustomBo>> queryAll(Integer pageNum);
}
