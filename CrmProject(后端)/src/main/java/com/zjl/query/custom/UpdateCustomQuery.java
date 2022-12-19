package com.zjl.query.custom;

import com.zjl.validated.Email;
import com.zjl.validated.Phone;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCustomQuery {

    @ApiModelProperty("客户地址")
    @NotNull(message = "id不可以为空")
    private Integer id;

    @ApiModelProperty("客户地址")
    @NotBlank(message = "客户名字不可以为空")
    private String customName;

    @ApiModelProperty("客户地址")
    @NotBlank(message = "客户来源地不可以为空")
    private String customFrom;

    @ApiModelProperty("客户地址")
    @NotBlank(message = "客户工作不可以为空")
    private String customWork;

    @ApiModelProperty("客户地址")
    @Phone
    private String customTel;

    @ApiModelProperty("客户地址")
    @Email
    private String customEmail;

    @ApiModelProperty("客户地址")
    @NotBlank(message = "客户地址不可以为空")
    private String customAddr;
}
