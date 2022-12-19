package com.zjl.result;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
@ApiModel("返回基类")
public class ResultBean<T> {
    private Integer code;
    private String msg;
    private T data;
    private Long timestamp;
}
