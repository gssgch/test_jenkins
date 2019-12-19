package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Loop;
import com.zhansheng.framework.domain.UserManager.TbLoop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/6/14
 */


@Mapper
public interface TbLoopMapper {

    //添加环路
    public int addLoop(Loop loop);

    //删除环路
    public int deleteById(int loopId);

    //修改环路
    public int updateLoop(Loop loop);


    //删除所有环路信息
    public int deleteLoop();

    public List<Loop> findByloopIdAndDraughtfanList(int windmillId);


    @Select("select * from tb_loop")
    List<Loop> list();

    Loop findByLoopName(String loopnmae);

    //查询所有环路信息
    public List<TbLoop> findPageList(int windmillId);

    //查询一个环路下的所有风机信息
    public List<TbLoop> findByLoopIdAndList(@Param("windmillId") int windmillId, @Param("loopId") int loopId);

    //查询当前环路下的风机
    public int countLoop(int loopId);

}
