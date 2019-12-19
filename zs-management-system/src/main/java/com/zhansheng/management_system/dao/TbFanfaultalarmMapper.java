package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanfaultalarm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TbFanfaultalarmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbFanfaultalarm record);

    int updateByPrimaryKey(TbFanfaultalarm record);

    List<TbFanfaultalarm> findList();


}