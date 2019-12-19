package com.zhansheng.management_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author
 * @description
 * @date 2019/3/26
 */
@EnableDiscoveryClient //表示它是一个Eureka的客户端
@SpringBootApplication
@EntityScan("com.zhansheng.framework.domain.*")//扫描实体类
@ComponentScan(basePackages = {"com.zhansheng.api.*"})//扫描接口
@ComponentScan(basePackages = {"com.zhansheng.management_system.*"})//扫描本项目下的所有类
@MapperScan(basePackages = {"com.zhansheng.management_system.*"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class ManageUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageUserApplication.class, args);
    }


}
