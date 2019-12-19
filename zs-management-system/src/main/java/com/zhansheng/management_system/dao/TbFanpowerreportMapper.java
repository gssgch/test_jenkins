package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanpowerreport;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.request.PowerrePort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbFanpowerreportMapper {


    List<TbFanpowerreport> findByMonthAndDay2(@Param("powerrePort") PowerrePort powerrePort);
}