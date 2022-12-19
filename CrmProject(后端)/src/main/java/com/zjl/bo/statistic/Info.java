package com.zjl.bo.statistic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Info {
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("数量")
    private Integer count;
}
