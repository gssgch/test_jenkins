package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbAIrawselectfanStat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAIrawselectfanStatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAIrawselectfanStat record);

    int insertSelective(TbAIrawselectfanStat record);

    int updateByPrimaryKeySelective(TbAIrawselectfanStat record);

    int updateByPrimaryKey(TbAIrawselectfanStat record);

    //查询（原始字段选取风机统计表）
    List<TbAIrawselectfanStat> findListFan(@Param("selectkey") String selectkey);
}