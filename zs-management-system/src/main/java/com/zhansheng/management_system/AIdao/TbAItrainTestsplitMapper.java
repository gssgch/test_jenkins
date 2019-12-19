package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbAItrainTestsplit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAItrainTestsplitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAItrainTestsplit record);

    int insertSelective(TbAItrainTestsplit record);

    int updateByPrimaryKeySelective(TbAItrainTestsplit record);

    int updateByPrimaryKey(TbAItrainTestsplit record);

    //查询（训练集测试集切分数据表）
    List<TbAItrainTestsplit> findList(@Param("featureselkey") String featureselkey);
}