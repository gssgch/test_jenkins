package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbAIfeatureSelect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAIfeatureSelectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAIfeatureSelect record);

    int insertSelective(TbAIfeatureSelect record);

    int updateByPrimaryKeySelective(TbAIfeatureSelect record);

    int updateByPrimaryKey(TbAIfeatureSelect record);

    //查询（特征选取表）
    List<TbAIfeatureSelect> findList(@Param("featureselkey") String featureselkey, @Param("topic") String topic);
}