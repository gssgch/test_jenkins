package com.zhansheng.management_system.dao;


import com.zhansheng.framework.domain.UserManager.TbLoopWindmill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbWindmillLoopMapper {

    public int addWindmillAndLoop(@Param("windmillId") int windmillId, @Param("loopId") int loopId);

    public int deleteByWindmillIdAndLoopId();

    public int deleteWindmillLoop(int loopId);

    public List<TbLoopWindmill> find();



}
