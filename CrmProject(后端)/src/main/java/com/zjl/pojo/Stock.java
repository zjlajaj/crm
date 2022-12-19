package com.zjl.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName stock
 */
@TableName(value ="stock")
@Data
public class Stock implements Serializable {
    /**
     * 库存货物id
     */
    @TableId(type = IdType.AUTO)
    private Integer pkId;

    /**
     * 数量单位
     */
    private String stockUnit;

    /**
     * 库存名字
     */
    private String stockName;

    /**
     * 货物单价
     */
    private BigDecimal stockPrice;

    /**
     * 出货数量
     */
    private BigDecimal stockOut;

    /**
     * 货物余量
     */
    private BigDecimal stockLeft;

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
