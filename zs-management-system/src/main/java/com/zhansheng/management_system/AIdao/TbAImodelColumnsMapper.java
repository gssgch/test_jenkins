package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbAImodelcolumns;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAImodelColumnsMapper {

    List<TbAImodelcolumns> findColumns(@Param("modelkey") String modelkey);
}