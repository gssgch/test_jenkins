package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbAItrainResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAItrainResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAItrainResult record);

    int insertSelective(TbAItrainResult record);

    int updateByPrimaryKeySelective(TbAItrainResult record);

    int updateByPrimaryKey(TbAItrainResult record);

    //查询（训练结果表）
    List<TbAItrainResult> findList(@Param("modelkey") String modelkey);
}