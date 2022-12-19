package com.zjl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjl.pojo.Worker;
import com.zjl.query.worker.LoginQuery;
import com.zjl.result.ResultBean;
import com.zjl.service.WorkerService;
import com.zjl.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
* @author 12278
* @description 针对表【worker】的数据库操作Service实现
* @createDate 2022-12-08 18:30:13
*/
@Service
public class WorkerServiceImpl extends ServiceImpl<WorkerMapper, Worker>
    implements WorkerService{

}




