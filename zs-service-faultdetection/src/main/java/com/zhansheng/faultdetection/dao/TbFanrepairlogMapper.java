package com.zhansheng.faultdetection.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanrepairlog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbFanrepairlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbFanrepairlog record);

    int updateByPrimaryKey(TbFanrepairlog record);

    List<TbFanrepairlog> findList();

}