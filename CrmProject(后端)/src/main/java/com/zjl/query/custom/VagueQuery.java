package com.zjl.query.custom;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class VagueQuery {

    @ApiModelProperty("页码")
    @NotNull(message = "页码不可以为空")
    private Integer pageNum;

    @ApiModelProperty("客户地址")
    @NotBlank(message = "类型不可以为空")
    private String type;

    @ApiModelProperty("客户地址")
    @NotBlank(message = "关键字不可以为空")
    private String keyword;
}
