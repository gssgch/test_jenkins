package com.zhansheng.faultdetection.dao;

import com.zhansheng.framework.domain.UserManager.Loop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/7/10
 */
@Mapper
public interface TbLoopDtoMapper {

    public List<Loop> findByloopIdAndDraughtfanList(int windmillId);


}
