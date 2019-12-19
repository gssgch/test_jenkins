package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbParticularsMysql;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbParticularsMapper {

    //查询所有详情数据
    List<TbParticularsMysql> findList();

    //查询风机详情
    TbParticularsMysql particularOne(Integer draughtNumber);
}