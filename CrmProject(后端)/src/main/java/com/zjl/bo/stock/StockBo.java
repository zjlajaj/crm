package com.zjl.bo.stock;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockBo {
    @ApiModelProperty("id")
    private Integer id;
    /**
     * 数量单位
     */
    @ApiModelProperty("数量单位")
    private String stockUnit;

    /**
     * 库存名字
     */
    @ApiModelProperty("库存名字")
    private String stockName;

    /**
     * 货物单价
     */
    @ApiModelProperty("货物单价")
    private BigDecimal stockPrice;

    /**
     * 出货数量
     */
    @ApiModelProperty("出货数量")
    private BigDecimal stockOut;

    /**
     * 货物余量
     */
    @ApiModelProperty("货物余量")
    private BigDecimal stockLeft;
}
