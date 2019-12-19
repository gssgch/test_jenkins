package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbAIfeatureEngin;
import com.zhansheng.framework.domain.aiManager.TbAIrawfieldselect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAIfeatureEnginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAIfeatureEngin record);

    int insertSelective(TbAIfeatureEngin record);

    int updateByPrimaryKeySelective(TbAIfeatureEngin record);

    int updateByPrimaryKey(TbAIfeatureEngin record);

    //查询（特征工程表）
    List<TbAIfeatureEngin> findList(@Param("featurekey") String featurekey, @Param("topic") String topic);

}