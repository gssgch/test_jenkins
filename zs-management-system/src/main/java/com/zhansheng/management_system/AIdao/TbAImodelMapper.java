package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbAIfeatureSelect;
import com.zhansheng.framework.domain.aiManager.TbAImodel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAImodelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAImodel record);

    int insertSelective(TbAImodel record);

    int updateByPrimaryKeySelective(TbAImodel record);

    int updateByPrimaryKey(TbAImodel record);

    //查询（模型表）
    List<TbAImodel> findList(@Param("modelkey") String modelkey, @Param("topic") String topic);

    //通过专题，特征选取，算法查询
    List<TbAImodel> findListByThreeKey(@Param("topic") String topic,
                                       @Param("featureselkey") String featureselkey,
                                       @Param("algokey") String algokey);

}