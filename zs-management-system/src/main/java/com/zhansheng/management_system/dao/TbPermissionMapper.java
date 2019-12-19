package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.TbPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbPermissionMapper {

    //查询权限三级列表
    public List<TbPermission> listParents();

    //通过角色Id查询权限ID
    public List<TbPermission> findRoleIdByPermissionIdAndPermissionName(int roleId);

}
