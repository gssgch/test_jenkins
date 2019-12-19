package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbDictKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbDictMapper {


    int deleteByPrimaryKey(TbDictKey key);

    int insert(TbDictKey record);

    List<TbDictKey> findByKey(String key);



    List<TbDictKey> findByList();


}