package com.baayso.springcloud.demo.dao;

import java.util.List;

import com.baayso.springcloud.common.datasource.mybatis.mapper.BaseMapper;
import com.baayso.springcloud.demo.domain.DemoUserDO;


/**
 * 测试数据访问。
 *
 * @author ChenFangjie (2016/4/1 16:24)
 * @since 0.1
 */
public interface DemoUserDAO extends BaseMapper<DemoUserDO> {

    List<DemoUserDO> listUnion();

    List<DemoUserDO> listInnerJoin();

    List<DemoUserDO> listLeftJoin();

    List<DemoUserDO> listRightJoin();

    List<DemoUserDO> listSubQuery();

    int inserts();

    int insertIntoSelect();

}
