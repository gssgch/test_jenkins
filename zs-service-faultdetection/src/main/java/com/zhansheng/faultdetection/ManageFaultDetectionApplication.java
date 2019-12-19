package com.zhansheng.faultdetection;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient //表示它是一个Eureka的客户端
@SpringBootApplication
@EntityScan("com.zhansheng.framework.domain.*")//扫描实体类
@ComponentScan(basePackages = {"com.zhansheng.api.*"})//扫描接口
@ComponentScan(basePackages = {"com.zhansheng.faultdetection.*"})//扫描本项目下的所有类
@MapperScan(basePackages = {"com.zhansheng.faultdetection.dao.*"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class ManageFaultDetectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageFaultDetectionApplication.class, args);
    }
}
