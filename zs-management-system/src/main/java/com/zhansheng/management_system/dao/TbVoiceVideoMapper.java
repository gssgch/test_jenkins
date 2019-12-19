package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbVoiceVideo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbVoiceVideoMapper {

    List<TbVoiceVideo> findByFanNum(Integer fanNum);

}