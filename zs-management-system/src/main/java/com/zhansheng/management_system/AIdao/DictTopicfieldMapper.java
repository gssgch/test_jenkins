package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbDictTopicfield;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhansheng
 */

public interface DictTopicfieldMapper {

//    @Select("select * from dict_topicfield where F_DictType = #{fDicttype}")
    List<TbDictTopicfield> findByTopic(@Param("fDicttype") String fDicttype);
}
