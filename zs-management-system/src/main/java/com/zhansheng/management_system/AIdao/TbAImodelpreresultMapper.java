package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbAImodelpreresult;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TbAImodelpreresultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAImodelpreresult record);

    int insertSelective(TbAImodelpreresult record);

    int updateByPrimaryKeySelective(TbAImodelpreresult record);

    int updateByPrimaryKey(TbAImodelpreresult record);

    //查询（模型预测结果表）
    List<TbAImodelpreresult> findList(@Param("paramkey") String paramkey);
}