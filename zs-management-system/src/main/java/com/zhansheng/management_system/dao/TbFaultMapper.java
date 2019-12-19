package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFault;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbFaultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbFault record);

    int updateByPrimaryKey(TbFault record);
}