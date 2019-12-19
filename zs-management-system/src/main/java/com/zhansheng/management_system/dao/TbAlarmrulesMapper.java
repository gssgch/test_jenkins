package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmreceiver;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmrules;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;


@Mapper
public interface TbAlarmrulesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAlarmrules ecord);

    int updateByPrimaryKey(TbAlarmrules record);

    //告警处理人表的name是否存在
    List<TbAlarmrules> findByfkAlarmreceiver(int id);

    //分页查询
    List<TbAlarmrules> findList();


}