package com.yue.yojbackendjudgeservice;

import com.yue.yojbackendjudgeservice.rabbitmq.InitRabbitMq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.yue.yojbackendclientservice.service"})
public class YojBackendJudgeServiceApplication {

    public static void main(String[] args) {
        InitRabbitMq.doInit();
        SpringApplication.run(YojBackendJudgeServiceApplication.class, args);
    }

}
