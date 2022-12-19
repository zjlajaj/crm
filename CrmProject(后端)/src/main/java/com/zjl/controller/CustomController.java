package com.zjl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjl.bo.custom.CustomBo;
import com.zjl.mail.Mail;
import com.zjl.mail.MailService;
import com.zjl.pojo.Custom;
import com.zjl.query.custom.AddCustomQuery;
import com.zjl.query.custom.UpdateCustomQuery;
import com.zjl.query.custom.VagueQuery;
import com.zjl.result.ResultBean;
import com.zjl.service.CustomService;
import com.zjl.service.strategy.custom.CustomQueryHandlerStrategy;
import com.zjl.service.strategy.custom.CustomQueryHandlerStrategyFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/custom")
@Api(tags = "客户管理接口")
@Transactional
public class CustomController {
    @Autowired
    private CustomService customService;

    @Autowired
    private MailService mailService;

    @ApiOperation("添加客户")
    @PostMapping("/addCustom")
    public ResultBean<Void> addCustom(@RequestBody @Validated AddCustomQuery addCustomQuery){
        ResultBean<Void> resultBean = new ResultBean<>();
        Custom custom=new Custom();
        custom.setCustomAddr(addCustomQuery.getCustomAddr());
        custom.setCustomFrom(addCustomQuery.getCustomFrom());
        custom.setCustomEmail(addCustomQuery.getCustomEmail());
        custom.setCustomName(addCustomQuery.getCustomName());
        custom.setCustomTel(addCustomQuery.getCustomTel());
        custom.setCustomWork(addCustomQuery.getCustomWork());
        boolean save = customService.save(custom);
        if (save){
            resultBean.setCode(HttpStatus.OK.value())
                    .setMsg("添加成功");
        }
        else {
            resultBean.setMsg("未知原因添加失败")
                    .setCode(HttpStatus.BAD_REQUEST.value());
        }
        return resultBean.setTimestamp(System.currentTimeMillis());
    }

    @ApiOperation("模糊查询,type有addr,name,tel")
    @PostMapping ("/vagueQuery")
    public ResultBean<IPage<CustomBo>> vagueQuery(@RequestBody @Validated VagueQuery vagueQuery){
        ResultBean<IPage<CustomBo>> resultBean = new ResultBean<>();
        CustomQueryHandlerStrategy strategy = CustomQueryHandlerStrategyFactory.getStrategy(vagueQuery.getType());
        if (strategy==null){
            return resultBean.setTimestamp(System.currentTimeMillis())
                            .setData(null)
                            .setMsg("类型非法")
                            .setCode(HttpStatus.OK.value());
        }
        IPage<CustomBo> page1= strategy.VagueQuery(vagueQuery.getPageNum(), vagueQuery.getKeyword());
        return resultBean.setTimestamp(System.currentTimeMillis())
                        .setData(page1)
                        .setMsg("查询成功")
                        .setCode(HttpStatus.OK.value());
    }

    @GetMapping("/queryCustom/{pageNum}")
    @ApiOperation("查询所有客户")
    public ResultBean<IPage<CustomBo>> queryCustom(@PathVariable("pageNum") Integer pageNum){
        return customService.queryAll(pageNum);
    }

    @ApiOperation("更新客户信息")
    @PutMapping("/updateCustom")
    public ResultBean<Void> updateCustom(@RequestBody @Validated UpdateCustomQuery updateCustomQuery){
        ResultBean<Void> resultBean = new ResultBean<>();
        Custom custom=new Custom();
        custom.setPkId(updateCustomQuery.getId());
        custom.setCustomAddr(updateCustomQuery.getCustomAddr());
        custom.setCustomFrom(updateCustomQuery.getCustomFrom());
        custom.setCustomEmail(updateCustomQuery.getCustomEmail());
        custom.setCustomName(updateCustomQuery.getCustomName());
        custom.setCustomTel(updateCustomQuery.getCustomTel());
        custom.setCustomWork(updateCustomQuery.getCustomWork());
        boolean b = customService.updateById(custom);
        if (b){
            resultBean.setCode(HttpStatus.OK.value())
                    .setMsg("更新成功");
        }
        else {
            resultBean.setMsg("未知原因更新失败")
                    .setCode(HttpStatus.BAD_REQUEST.value());
        }
        return resultBean.setTimestamp(System.currentTimeMillis());
    }

    @PostMapping("/sendMail")
    @ApiOperation("发送邮件")
    public ResultBean<Void> sendMail(@RequestBody @Validated Mail mail) throws Exception {
        ResultBean<Void> resultBean = new ResultBean<>();
        mailService.sendSimpleMail(mail);
        return resultBean.setTimestamp(System.currentTimeMillis())
                        .setData(null)
                        .setMsg("发送成功")
                        .setCode(HttpStatus.OK.value());
    }


}
