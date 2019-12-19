package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultLoc;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TbFaultLocMapper {
    int deleteByPrimaryKey(Integer faultLocNum);

    int insert(TbFaultLoc record);

    int updateByPrimaryKey(TbFaultLoc record);

    //查询告警位置Id 和 具体位置
    List<TbFaultLoc> findLoc();

    List<TbFaultLoc> findList();

    int updateNotFile(TbFaultLoc tbFaultLoc);
}