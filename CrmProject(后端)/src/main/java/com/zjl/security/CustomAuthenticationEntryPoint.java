package com.zjl.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjl.exception.CustomerAuthenionException;
import com.zjl.result.ResultBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        ResultBean<Void> resultBean = new ResultBean<>();
        resultBean.setMsg("未认证")
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
