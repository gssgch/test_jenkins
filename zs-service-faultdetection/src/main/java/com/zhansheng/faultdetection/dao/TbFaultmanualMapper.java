package com.zhansheng.faultdetection.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultmanual;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbFaultmanualMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbFaultmanual record);

    int updateByPrimaryKey(TbFaultmanual record);

    List<TbFaultmanual> findList(@Param("faultname") String faultname);

}