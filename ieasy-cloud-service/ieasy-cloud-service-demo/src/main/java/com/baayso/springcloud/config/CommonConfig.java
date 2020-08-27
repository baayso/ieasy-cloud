package com.baayso.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.baayso.commons.security.AESCoder;
import com.baayso.commons.security.password.BCryptPasswordEncoder;
import com.baayso.commons.security.password.PasswordEncoder;
import com.baayso.commons.spring.SpringUtils;
import com.baayso.commons.utils.Validator;

/**
 * 公共配置。
 *
 * @author ChenFangjie (2016/4/11 15:52)
 * @since 0.1
 */
@Configuration
public class CommonConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    @Bean
    public static SpringUtils springUtils() {
        return new SpringUtils();
    }

    @Bean
    public AESCoder aesCoder() {
        return new AESCoder("G7jX/RpqbqzbvUoxJ2fEaVdhk8e/axGXbhEXli2dR0TI=");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Validator validator() {
        return new Validator();
    }

}
