package com.zhansheng.management_system.dao;


import com.zhansheng.framework.domain.UserManager.TbDraughtfan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbDraughtfanMapper {

    //删除所有风机信息
    public int deleteDraughtfan();

    //删除已条风机信息
    public int deleteById(int draughtfanId);

    //添加风机
    public int addDraughtfan(TbDraughtfan tbDraughtfan);

    //修改风机信息
    public int updateDraughtfan(TbDraughtfan draughtfan);

    public List<TbDraughtfan> findByloopIdAndDraughtfanList(int loopId);

    //查询当前环路下所有风机
    List<TbDraughtfan> findByLoopIdList(Integer loopId);

    public List<TbDraughtfan> findDraughtfanNO();



}
