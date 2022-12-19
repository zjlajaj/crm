package com.zjl.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjl.result.ResultBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAccessDenied implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResultBean<Void> resultBean = new ResultBean<>();
        resultBean.setMsg("权限不足")
                .setTimestamp(System.currentTimeMillis())
                .setCode(HttpStatus.UNAUTHORIZED.value());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        String resBody = objectMapper.writeValueAsString(resultBean);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(resBody);
        printWriter.flush();
        printWriter.close();
    }
}
