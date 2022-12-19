package com.zjl.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName custom
 */
@TableName(value ="custom")
@Data
public class Custom implements Serializable {
    /**
     * 客户编号
     */
    @TableId(type = IdType.AUTO)
    private Integer pkId;

    /**
     * 客户姓名
     */
    private String customName;

    /**
     * 客户来源
     */
    private String customFrom;

    /**
     * 客户工作
     */
    private String customWork;

    /**
     * 客户电话
     */
    private String customTel;

    /**
     * 客户电子邮箱
     */
    private String customEmail;

    /**
     * 客户地址
     */
    private String customAddr;

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
