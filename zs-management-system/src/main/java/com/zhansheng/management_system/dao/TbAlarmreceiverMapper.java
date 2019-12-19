package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmreceiver;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;


@Mapper
public interface TbAlarmreceiverMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAlarmreceiver record);

    int updateByPrimaryKey(TbAlarmreceiver record);

    List<TbAlarmreceiver> findList();

    //查询告警处理人
    /*List<TbAlarmreceiver> findAlar();*/


}