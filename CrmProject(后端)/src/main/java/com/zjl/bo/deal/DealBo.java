package com.zjl.bo.deal;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DealBo {
    /**
     * 订单编号
     */
    @Excel(name = "ID")
    @ApiModelProperty("ID")
    private Integer pkId;

    /**
     * 订单名称
     */
    @Excel(name = "订单名称",width = 20,needMerge = true)
    @ApiModelProperty("订单名称")
    private String dealName;

    /**
     * 订单客户
     */
    @Excel(name = "订单客户",width = 20,needMerge = true)
    @ApiModelProperty("订单客户")
    private String dealCustom;

    /**
     * 收货人
     */
    @Excel(name = "收货人",width = 20,needMerge = true)
    @ApiModelProperty("收货人")
    private String dealPeople;

    /**
     * 收货地址
     */
    @Excel(name = "收货地址",width = 20,needMerge = true)
    @ApiModelProperty("收货地址")
    private String dealAddr;

    /**
     * 收获号码
     */
    @Excel(name = "收获号码",width = 20,needMerge = true,desensitizationRule = "3_4")
    @ApiModelProperty("收获号码")
    private String dealTel;

    /**
     * 下单时间
     */
    @Excel(name = "下单时间",width = 20, format = "yyyy-MM-dd")
    @ApiModelProperty("下单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date dealTime;

    /**
     * 处理员工
     */
    @Excel(name = "处理员工",width = 20,needMerge = true)
    @ApiModelProperty("处理员工")
    private String dealWorker;

    /**
     * 下单商品
     */
    @Excel(name = "下单商品",width = 20,needMerge = true)
    @ApiModelProperty("下单商品")
    private String dealStock;

    /**
     * 下单数量
     */
    @Excel(name = "下单数量",width = 20,needMerge = true)
    @ApiModelProperty("下单数量")
    private BigDecimal dealNum;

    /**
     * 数量单位
     */
    @Excel(name = "数量单位",width = 20,needMerge = true)
    @ApiModelProperty("数量单位")
    private String dealUnit;

    /**
     * 备注
     */
    @Excel(name = "备注",width = 20,needMerge = true)
    @ApiModelProperty("备注")
    private String dealDetail;

    /**
     * 订单状态，0为未发货，1为已发货，2为已收货
     */
    @Excel(name = "订单状态",width = 20,needMerge = true,replace = {"未发货_0", "已发货_1","已收货_2"})
    @ApiModelProperty("订单状态，0为未发货，1为已发货，2为已收货")
    private String dealState;
}
