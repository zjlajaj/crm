package com.zjl.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName admin
 */
@TableName(value ="admin")
@Data
public class Admin implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer pkId;

    /**
     *
     */
    private String ukAdminUsername;

    /**
     *
     */
    private String adminPassword;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
