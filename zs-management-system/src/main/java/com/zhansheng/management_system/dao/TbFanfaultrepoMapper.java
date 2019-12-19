package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanfaultrepo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbFanfaultrepoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TbFanfaultrepo record);

    List<TbFanfaultrepo> findList();

    int updateByPrimaryKey(TbFanfaultrepo record);
}