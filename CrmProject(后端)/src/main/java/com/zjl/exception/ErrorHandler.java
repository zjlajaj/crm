package com.zjl.exception;

import com.zjl.result.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    public ResultBean<?> handleExption(Exception e) {
        ResultBean<Void> resultBean = new ResultBean<Void>();
        resultBean.setTimestamp(System.currentTimeMillis());
        resultBean.setData(null);
        if (e instanceof BindException) {
            BindException ex = (BindException) e;
            resultBean.setMsg(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage() + "");
            return resultBean;
        }
        if (e instanceof org.springframework.web.bind.MissingServletRequestParameterException){
            resultBean.setMsg("参数缺失");
            return resultBean;
        }
        if (e instanceof org.springframework.dao.DuplicateKeyException){
            resultBean.setMsg("用户名已经存在");
            return resultBean;
        }
        if (e instanceof AuthenticationException){
            resultBean.setCode(HttpStatus.UNAUTHORIZED.value())
                    .setMsg(e.getMessage());
            return resultBean;
        }
        resultBean.setMsg(e.toString());
        resultBean.setCode(HttpStatus.EXPECTATION_FAILED.value());
        System.out.println(e);
        return resultBean;
    }
}
