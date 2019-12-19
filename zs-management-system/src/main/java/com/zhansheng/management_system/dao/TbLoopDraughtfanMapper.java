package com.zhansheng.management_system.dao;


import com.zhansheng.framework.domain.UserManager.TbDraughtfanLoop;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbLoopDraughtfanMapper {

    //删除环路  风机关联信息
    public int deleteLoopAndDraughtfan();

    //通过loopId 删除环路风机表信息
    public int deleteByloopIdAndDraughtfan(int loopId);

    //通过风机环路中间表查询风机Id
    public List<TbDraughtfanLoop> findByLoopIdAndDraughtfanId(int loopId);

    //添加环路 风机ID
    @Insert("insert into tb_draughtfan_loop (F_loop_id, F_draught_id) values (#{loopId},#{fDraughtId})")
    public int addLoopIdAndDraughtId(@Param("loopId") Integer loopId, @Param("fDraughtId") Integer fDraughtId);

}
