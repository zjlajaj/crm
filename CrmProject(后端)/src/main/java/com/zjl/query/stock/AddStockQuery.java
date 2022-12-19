package com.zjl.query.stock;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AddStockQuery {

    /**
     * 数量单位
     */
    @NotBlank(message = "数量单位不可以为空")
    @ApiModelProperty("数量单位不可以为空")
    private String stockUnit;

    /**
     * 库存名字
     */
    @NotBlank(message = "库存名字不可以为空")
    @ApiModelProperty("库存名字不可以为空")
    private String stockName;

    /**
     * 货物单价
     */
    @NotNull(message = "货物单价不可以为空")
    @ApiModelProperty("货物单价不可以为空")
    private BigDecimal stockPrice;

    /**
     * 出货数量
     */
    @NotNull(message = "出货数量不可以为空")
    @ApiModelProperty("出货数量不可以为空")
    private BigDecimal stockOut;

    /**
     * 货物余量
     */
    @NotNull(message = "货物余量不可以为空")
    @ApiModelProperty("货物余量不可以为空")
    private BigDecimal stockLeft;
}
