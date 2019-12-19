package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbFaultTypeMapper {
    int deleteByPrimaryKey(Integer faultTypeNum);

    int insert(TbFaultType record);

    int updateByPrimaryKey(TbFaultType record);

    List<TbFaultType> findList();
}