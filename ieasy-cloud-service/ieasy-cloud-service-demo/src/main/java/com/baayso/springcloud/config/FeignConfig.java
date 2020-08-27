package com.baayso.springcloud.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.baayso.commons.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.codec.Decoder;
import feign.codec.Encoder;

/**
 * Feign配置。
 *
 * @author ChenFangjie (2020/8/12 10:59)
 * @since 0.1
 */
@Configuration
public class FeignConfig {

    @Bean
    public Decoder feignDecoder() {
        HttpMessageConverter<Object> converter = new MappingJackson2HttpMessageConverter(this.objectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(converter);

        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }

    @Bean
    public Encoder feignEncoder() {
        HttpMessageConverter<Object> converter = new MappingJackson2HttpMessageConverter(this.objectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(converter);

        return new SpringEncoder(objectFactory);
    }

    public ObjectMapper objectMapper() {
        return JsonUtils.INSTANCE.getMapper();
    }

}
