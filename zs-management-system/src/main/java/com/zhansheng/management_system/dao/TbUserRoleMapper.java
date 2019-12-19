package com.zhansheng.management_system.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbUserRoleMapper {

    public int deleteUserAndRole(int userId);

    public int addUserRoles(@Param("userId") int userId, @Param("roleIds") List<Integer> roleIds);

    public int deleteByRoleIdAndUserId(int roleId);
}


