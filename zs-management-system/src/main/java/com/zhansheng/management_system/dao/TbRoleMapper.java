package com.zhansheng.management_system.dao;


import com.zhansheng.framework.domain.UserManager.TbRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbRoleMapper {

    //添加角色及对应的权限
    public int addRole(TbRole role);

    //删除角色信息(包括用户关联,权限关联)
    public int deleteByRoleId(int roleId);

    //查询角色信息(包含分页,用户名模糊匹配)
    public List<TbRole> findPageAndList(String roleName);

    //查询角色信息
    public List<TbRole> findRoleList();

    //查询角色名称和角色id
    public List<TbRole> findByRoleName();

    //查询角色名称
    public TbRole findRoleName(String rolename);

    //修改角色信息
    public int updateRole(TbRole role);

    //查询用户对应的角色信息
    public List<TbRole> findUserIdByRoleIdAndRoleName(int userId);


    //用户添加查询角色名称
    public TbRole findRoleNameByRoleName(String roleName);
}
