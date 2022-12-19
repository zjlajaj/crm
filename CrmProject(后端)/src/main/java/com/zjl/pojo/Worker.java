package com.zjl.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName worker
 */
@TableName(value ="worker")
@Data
public class Worker implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer pkId;

    /**
     *
     */
    private String workName;

    /**
     *
     */
    private String ukWorkAccount;

    /**
     *
     */
    private String workPwd;

    /**
     *
     */
    private String workTel;

    /**
     *
     */
    private String workAddr;

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
