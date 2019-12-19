package com.zhansheng.faultdetection.dao;

import com.zhansheng.framework.domain.UserManager.TbDraughtfan;
import com.zhansheng.framework.domain.UserManager.TbLoop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/6/25
 */
@Mapper
public interface BaseMapper {

    //查询所有环路信息
    public List<TbLoop> findPageList(int windmillId);

    //查询一个环路下的所有风机信息
    public List<TbLoop> findByLoopIdAndList(@Param("windmillId") int windmillId, @Param("loopId") int loopId);

    //查询当前环路下的风机
    public int countLoop(int loopId);

    public int countDra();

    List<TbDraughtfan> findByLoopIdList(Integer loopId);

    //查询所有风机编号
    //List<TbDraughtfan> findByFdraughtid(int windmillId);


}
