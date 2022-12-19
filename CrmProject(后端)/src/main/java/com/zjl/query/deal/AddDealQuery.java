package com.zjl.query.deal;

import com.zjl.validated.Phone;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AddDealQuery {
    /**
     * 订单名称
     */
    @ApiModelProperty("订单名称")
    @NotBlank(message = "订单名称不可以为空")
    private String dealName;

    /**
     * 订单客户
     */
    @ApiModelProperty("订单客户")
    @NotBlank(message = "订单客户不可以为空")
    private String dealCustom;

    /**
     * 收货人
     */
    @ApiModelProperty("收货人")
    @NotBlank(message = "收货人不可以为空")
    private String dealPeople;

    /**
     * 收货地址
     */
    @ApiModelProperty("收货地址")
    @NotBlank(message = "收货地址不可以为空")
    private String dealAddr;

    /**
     * 收获号码
     */
    @ApiModelProperty("收货人手机号")
    @Phone
    private String dealTel;


    /**
     * 下单商品
     */
    @ApiModelProperty("库存id")
    @NotNull(message = "库存id不可以为空")
    private Integer dealStockId;

    /**
     * 下单数量
     */
    @ApiModelProperty("下单数量")
    @NotNull(message = "下单数量不可以为空")
    private BigDecimal dealNum;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String dealDetail;

    /**
     * 订单状态，0为未发货，1为已发货，2为已收货
     */
    @ApiModelProperty("订单状态，0为未发货，1为已发货，2为已收货")
    @NotBlank(message = "订单状态不可以为空")
    private String dealState;
}
