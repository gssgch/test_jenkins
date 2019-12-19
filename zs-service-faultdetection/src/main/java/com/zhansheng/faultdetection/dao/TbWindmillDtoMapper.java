package com.zhansheng.faultdetection.dao;

import com.zhansheng.framework.domain.UserManager.TbWindmill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/7/10
 */
@Mapper
public interface TbWindmillDtoMapper {

    //查询风厂信息
    public List<TbWindmill> findList();

}
