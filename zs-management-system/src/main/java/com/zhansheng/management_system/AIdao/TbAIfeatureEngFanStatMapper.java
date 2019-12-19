package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbAIfeatureEngFanStat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAIfeatureEngFanStatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAIfeatureEngFanStat record);

    int insertSelective(TbAIfeatureEngFanStat record);

    int updateByPrimaryKeySelective(TbAIfeatureEngFanStat record);

    int updateByPrimaryKey(TbAIfeatureEngFanStat record);

    //查询（特征工程表风机统计表）
    List<TbAIfeatureEngFanStat> findListFan(@Param("featurekey") String featurekey);
}