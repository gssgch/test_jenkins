package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFault;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TbFanFaultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbFault fault);

    int updateByPrimaryKey(TbFault fault);

    //分页查询
    List<TbFault> findList(Integer faultFrom);

    //查询故障总数
    int findFaultCount();


    /**
     * 查询20条故障信息
     * @Author xuzhengjie
     * @Date   2019/10/15 17:09
     * @return java.util.List<com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFault>
     *
     */
    List<TbFault> findFault();

}