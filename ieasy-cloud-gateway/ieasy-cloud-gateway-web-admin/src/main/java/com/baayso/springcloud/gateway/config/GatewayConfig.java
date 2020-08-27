package com.baayso.springcloud.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

/**
 * 网关配置。
 *
 * @author ChenFangjie (2020/8/12 17:45)
 * @since 0.1
 */
@Configuration
public class GatewayConfig {

    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

}
