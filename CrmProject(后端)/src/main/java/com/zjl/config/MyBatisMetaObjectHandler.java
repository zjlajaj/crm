package com.zjl.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Date;

@Configuration
public class MyBatisMetaObjectHandler implements MetaObjectHandler {


/*    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MyBatisMetaObjectHandler());
        return globalConfig;
    }*/

    /**
     * 自定义插入时填充规则
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date date=new Date();
        // 注意是类属性字段名称，不是表字段名称
        this.setFieldValByName("gmtCreate", date, metaObject);
        this.setFieldValByName("gmtModified", date, metaObject);
    }

    /**
     * 自定义更新时填充规则
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 注意是类属性字段名称，不是表字段名称
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }

}
