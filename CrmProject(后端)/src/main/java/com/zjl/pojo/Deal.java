package com.zjl.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName deal
 */
@TableName(value ="deal")
@Data
public class Deal implements Serializable {
    /**
     * 订单编号
     */
    @TableId(type = IdType.AUTO)
    private Integer pkId;

    /**
     * 订单名称
     */
    private String dealName;

    /**
     * 订单客户
     */
    private String dealCustom;

    /**
     * 收货人
     */
    private String dealPeople;

    /**
     * 收货地址
     */
    private String dealAddr;

    /**
     * 收获号码
     */
    private String dealTel;

    /**
     * 下单时间
     */
    private Date dealTime;

    /**
     * 处理员工
     */
    private String dealWorker;

    /**
     * 下单商品
     */
    private String dealStock;

    /**
     * 下单数量
     */
    private BigDecimal dealNum;

    /**
     * 数量单位
     */
    private String dealUnit;

    /**
     * 备注
     */
    private String dealDetail;

    /**
     * 订单状态，0为未发货，1为已发货，2为已收货
     */
    private String dealState;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
