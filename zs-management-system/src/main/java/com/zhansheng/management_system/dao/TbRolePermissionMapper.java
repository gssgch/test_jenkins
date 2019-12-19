package com.zhansheng.management_system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbRolePermissionMapper {

    //添加角色对应的权限
    public int addRolePermission(@Param("roleId") int roleId, @Param("permissionIds") List<Integer> permissionIds);

    //删除角色对应权限
    public int deleteRolePermission(int roleId);

}
