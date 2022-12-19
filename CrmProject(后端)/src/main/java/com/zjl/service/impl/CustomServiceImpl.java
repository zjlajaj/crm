package com.zjl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjl.bo.custom.CustomBo;
import com.zjl.pojo.Custom;
import com.zjl.result.ResultBean;
import com.zjl.service.CustomService;
import com.zjl.mapper.CustomMapper;
import com.zjl.system.SystemConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
* @author 12278
* @description 针对表【custom】的数据库操作Service实现
* @createDate 2022-12-08 18:29:47
*/
@Service
public class CustomServiceImpl extends ServiceImpl<CustomMapper, Custom>
    implements CustomService{

    @Autowired
    private CustomMapper customMapper;
    @Override
    public ResultBean<IPage<CustomBo>> queryAll(Integer pageNum) {
        ResultBean<IPage<CustomBo>> resultBean = new ResultBean<>();
        Page<CustomBo> page=new Page<>(pageNum, SystemConst.PAGE_SIZE);
        IPage<CustomBo> customBoIPage = customMapper.selectCustomBo(page);
        return resultBean.setTimestamp(System.currentTimeMillis())
                        .setData(customBoIPage)
                        .setMsg("查询成功")
                        .setCode(HttpStatus.OK.value());
    }
}




