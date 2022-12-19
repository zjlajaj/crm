package com.zjl.query.custom;

import com.zjl.validated.Email;
import com.zjl.validated.Phone;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddCustomQuery {

    @NotBlank(message = "客户名字不可以为空")
    @ApiModelProperty("客户名字")
    private String customName;

    @NotBlank(message = "客户来源地不可以为空")
    @ApiModelProperty("客户来源地")
    private String customFrom;

    @NotBlank(message = "客户工作不可以为空")
    @ApiModelProperty("客户工作")
    private String customWork;

    @Phone
    @ApiModelProperty("客户手机号")
    private String customTel;

    @Email
    @ApiModelProperty("客户邮箱地址")
    private String customEmail;

    @NotBlank(message = "客户地址不可以为空")
    @ApiModelProperty("客户地址")
    private String customAddr;
}
