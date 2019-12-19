package com.zhansheng.management_system.dao;


import com.zhansheng.framework.domain.UserManager.TbWindmill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbWindmillMapper {

    //添加风厂信息
    public int addWindmill(TbWindmill windmill);

    //删除 风厂 环路 风机
    public int deleteById(int windmillId);

    //修改风厂信息
    public int updateByWindmill(TbWindmill windmill);

    //查询风厂信息
    public List<TbWindmill> findList();

    //查询风厂id
    int findByWindmillId();

    //查询风厂名称
    TbWindmill findByWindmillName();

}
