package com.zjl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.zjl.mapper")
@EnableTransactionManagement
public class CrmProjectApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(CrmProjectApplication.class, args);
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
