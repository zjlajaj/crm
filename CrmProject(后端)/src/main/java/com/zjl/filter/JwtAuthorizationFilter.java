package com.zjl.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zjl.bo.LoginData;
import com.zjl.exception.CustomerAuthenionException;
import com.zjl.mapper.AdminMapper;
import com.zjl.mapper.WorkerMapper;
import com.zjl.pojo.Admin;
import com.zjl.pojo.Worker;
import com.zjl.utils.JWTUtil;
import com.zjl.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author
 * @description 过滤器处理所有HTTP请求，并检查是否存在带有正确令牌的Authorization标头。例如，如果令牌未过期或签名密钥正确。
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    private WorkerMapper workerMapper= SpringUtils.getBean("workerMapper");

    private AdminMapper adminMapper=SpringUtils.getBean("adminMapper");
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String token = request.getHeader("token");
        if (token == null) {
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
            return;
        }
        LoginData loginData = null;
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            loginData = JWTUtil.getLoginData(token);
        } catch (CustomerAuthenionException e) {
            logger.error("Invalid jwt : " + e.getMessage());
            chain.doFilter(request, response);
            return;
        }
        String password="";
        Object currentUser=null;
        if (loginData.getRole().equals("ROLE_work")){
            currentUser = workerMapper.selectById(loginData.getId());
            if (currentUser == null) {
                return;
            }
            password=((Worker) currentUser).getWorkPwd();
        }
        else {
            currentUser=adminMapper.selectById(1);
            if (currentUser == null) {
                return;
            }
            password=((Admin) currentUser).getAdminPassword();
        }
        JWTVerifier jwtVerifier =
                JWT.require(Algorithm.HMAC256(password)).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            logger.error("Invalid jwt : " + e.getMessage());
            chain.doFilter(request, response);
            return;
        }
        authentication = new UsernamePasswordAuthenticationToken(currentUser, null, Arrays.stream(loginData.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}


