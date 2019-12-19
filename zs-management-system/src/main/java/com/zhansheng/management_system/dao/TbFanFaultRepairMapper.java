package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanFaultRepair;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbFanFaultRepairMapper {

    int insert(TbFanFaultRepair record);

}