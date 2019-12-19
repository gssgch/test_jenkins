package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.TbStateMysql;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbStateMapper {
    int deleteByPrimaryKey(Integer fId);

    int insert(TbStateMysql record);

    int updateByPrimaryKey(TbStateMysql record);

    //查询最新的一条风机数据
    List<TbStateMysql> stateOne(int draught_number);


    //查询工控系统状态
    TbStateMysql findState1List(Integer draughtNumber);
}