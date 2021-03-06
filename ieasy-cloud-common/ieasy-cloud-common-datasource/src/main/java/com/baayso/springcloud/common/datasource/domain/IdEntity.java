package com.baayso.springcloud.common.datasource.domain;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一定义主键（id）的Entity基类。
 *
 * @author ChenFangjie (2016/4/11 14:53)
 * @since 0.1
 */
@Getter
@Setter
public abstract class IdEntity implements Serializable {

    private static final long serialVersionUID = 6836860712036654696L;

    /** JavaScript 无法处理 Java 的长整型 Long 导致精度丢失，具体表现为主键最后两位永远为 0，解决思路： Long 转为 String 返回 */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;

    public IdEntity() {
    }

    public IdEntity(Long id) {
        this.id = id;
    }

}
