package com.baayso.springcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 程序启动类。
 *
 * @author ChenFangjie (2020/3/6 13:15)
 * @since 0.1
 */
@EnableDiscoveryClient
@SpringBootApplication
public class WebAdminGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAdminGatewayApplication.class, args);
    }

}
