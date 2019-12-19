package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFault;
import com.zhansheng.framework.domain.UserManager.TbDraughtfan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BladeMonitorMapper {
    //分页查询
    List<TbDraughtfan> findList(@Param("faultFrom") Integer faultFrom, @Param("healthyState") Integer healthyState,
                                @Param("count") Integer count, @Param("fDraughtNumber") Integer fDraughtNumber);

    Integer findFanCount();

    List<TbDraughtfan> findFan();

    //故障历史查询
    List<TbFault> findFaultList(@Param("faultFrom") Integer faultFrom, @Param("fDraughtNumber") Integer fDraughtNumber);

    //查询各状态总数
    Map<String, Object> findCount(@Param("faultFrom") Integer faultFrom,
                                  @Param("count") Integer count);

}