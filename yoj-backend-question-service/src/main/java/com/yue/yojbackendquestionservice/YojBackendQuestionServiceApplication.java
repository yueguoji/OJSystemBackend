package com.yue.yojbackendquestionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.yue.yojbackendQuestionservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.yue")  //不加这个只能扫描本模块的
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.yue.yojbackendclientservice.service"})
public class YojBackendQuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YojBackendQuestionServiceApplication.class, args);
    }

}
