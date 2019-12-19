package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanfaultreport;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.request.PowerrePort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbFanfaultreportMapper {
    /*故障总数报表查询*/
    List<TbFanfaultreport> fanFaultSumNum(@Param("powerrePort") PowerrePort powerrePort);
    /*故障位置报表查询*/
    List<TbFanfaultreport> fanFaultLoc(@Param("powerrePort") PowerrePort powerrePort);
    /*故障级别报表查询*/
    List<TbFanfaultreport> fanFaultLevel(@Param("powerrePort") PowerrePort powerrePort);

}