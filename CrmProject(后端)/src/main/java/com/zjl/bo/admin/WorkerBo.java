package com.zjl.bo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class WorkerBo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("员工名字")
    private String workName;

    @ApiModelProperty("员工账户")
    private String workAccount;

    @ApiModelProperty("员工手机号")
    private String workTel;

    @ApiModelProperty("员工地址")
    private String workAddr;
}
