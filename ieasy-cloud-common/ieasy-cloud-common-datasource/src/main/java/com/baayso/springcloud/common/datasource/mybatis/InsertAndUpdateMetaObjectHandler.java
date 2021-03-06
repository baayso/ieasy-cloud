package com.baayso.springcloud.common.datasource.mybatis;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;

import com.baayso.commons.log.Log;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * 插入与更新时自动填充字段处理器。
 *
 * @author ChenFangjie (2020/8/12 18:55)
 * @since 0.1
 */
public class InsertAndUpdateMetaObjectHandler implements MetaObjectHandler {

    private static final Logger log = Log.get();

    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("start insert fill...");

        String username = "default";
        LocalDateTime now = LocalDateTime.now();

        // createBy 应当取当前操作用户的名称
        this.strictInsertFill(metaObject, "createBy", String.class, username);
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, now);

        // updateBy 应当取当前操作用户的名称
        this.strictUpdateFill(metaObject, "updateBy", String.class, username);
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, now);

        log.debug("end insert fill.");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill...");

        // updateBy 应当取当前操作用户的名称
        this.strictUpdateFill(metaObject, "updateBy", String.class, "default");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

        log.info("end update fill.");
    }

}
