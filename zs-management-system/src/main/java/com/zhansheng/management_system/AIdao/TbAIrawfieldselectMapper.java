package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbAIrawfieldselect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAIrawfieldselectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAIrawfieldselect record);

    int insertSelective(TbAIrawfieldselect record);

    int updateByPrimaryKeySelective(TbAIrawfieldselect record);

    int updateByPrimaryKey(TbAIrawfieldselect record);

    //查询（原始字段选取表）
    List<TbAIrawfieldselect> findList(@Param("selectkey") String selectkey);

}