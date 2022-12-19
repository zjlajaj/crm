package com.zjl.config;

import com.zjl.filter.JwtAuthorizationFilter;
import com.zjl.security.CustomAccessDenied;
import com.zjl.security.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity  //启用Spring Security
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomAccessDenied customAccessDenied;
    public static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html",
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/webjars/**",
            "/doc.html"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        //官方推荐这种加密方式
        return new BCryptPasswordEncoder();
    }


    /**
     * 配置权限资源
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(withDefaults())
                // 禁用 CSRF
                .csrf().disable()
                .authorizeRequests()
                // swagger
                .antMatchers(SWAGGER_WHITELIST).permitAll()
                // 登录接口
                .antMatchers("/worker/login").permitAll()
                .antMatchers("/admin/login").permitAll()
                // 指定路径下的资源需要验证了的用户才能访问,可以配置多个角色
                .antMatchers("/admin/**").hasAnyRole("admin")
                .anyRequest().authenticated()
                .and()
                //添加自定义Filter
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                // 不需要session（不创建会话）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDenied);
        // 防止H2 web 页面的Frame 被拦截
        http.headers().frameOptions().disable();
    }
/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailsService);
    }*/
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(singletonList("*"));
        // configuration.setAllowedOriginPatterns(singletonList("*"));
        configuration.setAllowedHeaders(singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        configuration.setExposedHeaders(singletonList("Authorization"));
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
