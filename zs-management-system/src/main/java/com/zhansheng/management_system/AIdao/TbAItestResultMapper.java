package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbAItestResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAItestResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAItestResult record);

    int insertSelective(TbAItestResult record);

    int updateByPrimaryKeySelective(TbAItestResult record);

    int updateByPrimaryKey(TbAItestResult record);

    //查询（测试结果表）
    List<TbAItestResult> findList(@Param("modelkey") String modelkey);
}