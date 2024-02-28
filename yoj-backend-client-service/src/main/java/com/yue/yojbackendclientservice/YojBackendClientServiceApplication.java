package com.yue.yojbackendclientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class YojBackendClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YojBackendClientServiceApplication.class, args);
    }

}
