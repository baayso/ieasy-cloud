package com.baayso.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 示例程序启动类。
 *
 * @author ChenFangjie (2020/8/22 11:55)
 * @since 0.1
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
