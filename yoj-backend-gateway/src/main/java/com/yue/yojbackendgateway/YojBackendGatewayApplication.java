package com.yue.yojbackendgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)  //使用exclude排除掉数据库的使用
@EnableDiscoveryClient
public class YojBackendGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(YojBackendGatewayApplication.class, args);
    }

}
