package com.zhansheng.auth.dao;

import com.zhansheng.framework.domain.UserManager.TbPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbPermissionMapper {


    //通过用户id查询权限
    public List<TbPermission> selectPermissionByUserId(int userId);




}
