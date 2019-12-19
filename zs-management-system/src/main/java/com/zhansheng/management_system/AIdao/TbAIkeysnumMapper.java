package com.zhansheng.management_system.AIdao;

import com.zhansheng.framework.domain.aiManager.TbAIKeysnum;
import com.zhansheng.framework.domain.aiManager.TbAIfeatureSelect;

import java.util.List;

/**
 * @author zhansheng
 */
public interface TbAIkeysnumMapper {

    List<TbAIKeysnum> findList();

    String getNumByKey(String topickey);

    int updateByPrimaryKey(TbAIKeysnum record);

    int insert(TbAIKeysnum record);


}
