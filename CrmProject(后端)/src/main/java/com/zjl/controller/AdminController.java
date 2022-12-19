package com.zjl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjl.bo.LoginData;
import com.zjl.bo.admin.WorkerBo;
import com.zjl.pojo.Admin;
import com.zjl.pojo.Worker;
import com.zjl.query.admin.AddWorkerQuery;
import com.zjl.query.admin.UpdateWorkerQuery;
import com.zjl.query.worker.LoginQuery;
import com.zjl.result.ResultBean;
import com.zjl.service.AdminService;
import com.zjl.service.WorkerService;
import com.zjl.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Transactional
@Api(tags = "管理员管理接口")
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminService adminService;

    @Autowired
    private WorkerService workerService;

    @ApiOperation("添加管理员")
    @PostMapping("/admin/addAdmin")
    public ResultBean<Void> addAdmin(@RequestBody @Validated LoginQuery loginQuery) {
        String encode = passwordEncoder.encode(loginQuery.getPassword());
        Admin admin = new Admin();
        admin.setUkAdminUsername(loginQuery.getUsername());
        admin.setAdminPassword(encode);
        adminService.save(admin);
        return new ResultBean<Void>().setTimestamp(System.currentTimeMillis())
                .setData(null)
                .setMsg("添加成功")
                .setCode(HttpStatus.OK.value());
    }

    @ApiOperation("管理员登陆")
    @PostMapping("/admin/login")
    public ResultBean<String> adminLogin(@RequestBody @Validated LoginQuery loginQuery){
        ResultBean<String> resultBean = new ResultBean<>();
        Admin admin = adminService.getOne(new QueryWrapper<Admin>().select("admin_password","pk_id")
                .eq("uk_admin_username", loginQuery.getUsername()));
        if (admin==null){
            resultBean.setMsg("不存在此账户")
                    .setCode(HttpStatus.BAD_REQUEST.value());
            return resultBean.setTimestamp(System.currentTimeMillis());
        }
        boolean matches = passwordEncoder.matches(loginQuery.getPassword(), admin.getAdminPassword());
        if (matches){
            LoginData loginData=new LoginData(admin.getPkId(),"ROLE_admin", admin.getAdminPassword());
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

    @ApiOperation("添加员工")
    @PostMapping("/admin/addWorker")
    public ResultBean<Void> addWorker(@RequestBody @Valid AddWorkerQuery addWorkerQuery){
        ResultBean<Void> resultBean = new ResultBean<>();
        Worker worker=new Worker();
        worker.setWorkAddr(addWorkerQuery.getWorkAddr());
        worker.setWorkPwd(passwordEncoder.encode(addWorkerQuery.getWorkPwd()));
        worker.setUkWorkAccount(addWorkerQuery.getWorkAccount());
        worker.setWorkTel(addWorkerQuery.getWorkTel());
        worker.setWorkName(addWorkerQuery.getWorkName());
        workerService.save(worker);
        return resultBean.setTimestamp(System.currentTimeMillis())
                        .setData(null)
                        .setMsg("添加成功")
                        .setCode(HttpStatus.OK.value());
    }

    @ApiOperation("查询员工列表")
    @GetMapping("admin/queryWorkers/{pageNum}")
    public ResultBean<IPage<WorkerBo>> queryWorkers(@PathVariable("pageNum") Integer pageNum){
        return adminService.queryAll(pageNum);
    }

    @ApiOperation("删除员工")
    @DeleteMapping("admin/deleteWorker/{id}")
    public ResultBean<Void> deleteWorker(@PathVariable("id") Integer id){
        ResultBean<Void> resultBean = new ResultBean<>();
        boolean b = workerService.removeById(id);
        if (b){
            resultBean.setCode(HttpStatus.OK.value())
                    .setMsg("删除成功");
        }
        else {
            resultBean.setCode(HttpStatus.BAD_REQUEST.value())
                    .setMsg("未知原因删除失败");
        }
        return resultBean.setTimestamp(System.currentTimeMillis());
    }

    @ApiOperation("更新员工信息")
    @PutMapping("admin/updateWorker")
    public ResultBean<Void> updateWorker(@RequestBody @Validated UpdateWorkerQuery updateWorkerQuery){
        ResultBean<Void> resultBean = new ResultBean<>();
        Worker worker=new Worker();
        worker.setWorkName(updateWorkerQuery.getWorkName());
        worker.setWorkTel(updateWorkerQuery.getWorkTel());
        worker.setWorkAddr(updateWorkerQuery.getWorkAddr());
        worker.setUkWorkAccount(updateWorkerQuery.getWorkAccount());
        worker.setPkId(updateWorkerQuery.getId());
        boolean b = workerService.updateById(worker);
        if (b){
            resultBean.setCode(HttpStatus.OK.value())
                    .setMsg("更新成功");
        }
        else {
            resultBean.setCode(HttpStatus.BAD_REQUEST.value())
                    .setMsg("未知原因更新失败");
        }
        return resultBean.setTimestamp(System.currentTimeMillis());
    }

    @ApiOperation("重置员工密码")
    @PutMapping("admin/resetPwd/{id}")
    public ResultBean<Void> resetPwd(@PathVariable("id")Integer id){
        ResultBean<Void> resultBean = new ResultBean<>();
        String encode = passwordEncoder.encode("123456");
        Worker worker=new Worker();
        worker.setPkId(id);
        worker.setWorkPwd(encode);
        boolean b = workerService.updateById(worker);
        if (b){
            resultBean.setCode(HttpStatus.OK.value())
                    .setMsg("重置成功");
        }
        else {
            resultBean.setCode(HttpStatus.BAD_REQUEST.value())
                    .setMsg("未知原因重置失败");
        }
        return resultBean.setTimestamp(System.currentTimeMillis());
    }
}
