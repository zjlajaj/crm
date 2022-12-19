package com.zjl.query.admin;

import com.zjl.validated.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("添加员工类")
public class AddWorkerQuery {
    @ApiModelProperty("姓名")
    @NotBlank(message = "姓名不可以为空")
    private String workName;

    @ApiModelProperty("用户账户")
    @Length(min = 5,max = 20,message = "用户账户要求长度5到20")
    private String workAccount;

    @ApiModelProperty("用户密码")
    @Length(min = 5,max = 20,message = "用户密码要求长度5到20")
    private String workPwd;

    @ApiModelProperty("用户手机号")
    @Phone
    private String workTel;

    @ApiModelProperty("地址")
    @NotBlank(message = "地址不可以为空")
    private String workAddr;
}
