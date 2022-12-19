package com.zjl.query.admin;

import com.zjl.validated.Phone;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateWorkerQuery {
    @NotNull(message = "id不可以为空")
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("姓名")
    @NotBlank(message = "姓名不可以为空")
    private String workName;

    @ApiModelProperty("用户账户")
    @Length(min =5,max = 20)
    private String workAccount;

    @Phone
    @ApiModelProperty("手机号/")
    private String workTel;

    @ApiModelProperty("地址")
    @NotBlank(message = "地址不可以为空")
    private String workAddr;
}
