package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbAIalgorithm;

import java.util.List;

public interface TbAIalgorithmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAIalgorithm record);

    int insertSelective(TbAIalgorithm record);

    int updateByPrimaryKeySelective(TbAIalgorithm record);

    int updateByPrimaryKey(TbAIalgorithm record);

    //查询（原始字段选取表）
    List<TbAIalgorithm> findList(String algokey, String topic);
}