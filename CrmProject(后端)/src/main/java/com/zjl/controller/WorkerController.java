package com.zjl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjl.bo.LoginData;
import com.zjl.pojo.Worker;
import com.zjl.query.worker.LoginQuery;
import com.zjl.result.ResultBean;
import com.zjl.service.WorkerService;
import com.zjl.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "员工接口")
@Transactional
public class WorkerController {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/worker/login")
    @ApiOperation("员工登录")
    public ResultBean<String> workerLogin(@RequestBody @Valid LoginQuery loginQuery){
        ResultBean<String> resultBean = new ResultBean<>();
        Worker worker = workerService.getOne(new QueryWrapper<Worker>().select("work_pwd", "pk_id")
                .eq("uk_work_account", loginQuery.getUsername()));
        if (worker==null){
            resultBean.setMsg("不存在此账户")
                    .setCode(HttpStatus.BAD_REQUEST.value());
            return resultBean.setTimestamp(System.currentTimeMillis());
        }
        boolean matches = passwordEncoder.matches(loginQuery.getPassword(), worker.getWorkPwd());
        if (matches){
            LoginData loginData=new LoginData(worker.getPkId(),"ROLE_work", worker.getWorkPwd());
            String token = JWTUtil.createToken(loginData);
            resultBean.setMsg("登录成功")
                    .setData(token)
                    .setCode(HttpStatus.OK.value());
        }
        else {
            resultBean.setMsg("密码错误")
                    .setCode(HttpStatus.BAD_REQUEST.value());
        }
        return resultBean.setTimestamp(System.currentTimeMillis());
    }
}
