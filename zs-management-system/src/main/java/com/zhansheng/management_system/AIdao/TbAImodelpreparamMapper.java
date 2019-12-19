package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbAImodelpreparam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAImodelpreparamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAImodelpreparam record);

    int insertSelective(TbAImodelpreparam record);

    int updateByPrimaryKeySelective(TbAImodelpreparam record);

    int updateByPrimaryKey(TbAImodelpreparam record);

    //查询（模型预测参数表）
    List<TbAImodelpreparam> findList(@Param("paramkey") String paramkey);
}