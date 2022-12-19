package com.zjl.bo.custom;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CustomBo {
    @ApiModelProperty("id")
    private Integer pkId;

    @ApiModelProperty("客户名字")
    private String customName;

    @ApiModelProperty("客户来源地")
    private String customFrom;

    @ApiModelProperty("客户工作")
    private String customWork;

    @ApiModelProperty("客户手机号")
    private String customTel;

    @ApiModelProperty("客户邮箱")
    private String customEmail;

    @ApiModelProperty("客户地址")
    private String customAddr;
}
