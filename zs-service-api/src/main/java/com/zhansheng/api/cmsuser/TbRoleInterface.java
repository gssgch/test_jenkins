package com.zhansheng.api.cmsuser;


import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbPermission;
import com.zhansheng.framework.domain.UserManager.request.PageRoleName;
import com.zhansheng.framework.domain.UserManager.request.RoleDto;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "角色管理接口", description = "角色管理接口，提供增、删、改、查方法")
public interface TbRoleInterface {




   /* @ApiOperation("添加修改角色及对应的权限(id==0=修改....)")
    public Result saveRole(RoleDto roleDto);*/

    @ApiOperation("添加角色信息(关联权限)")
    public ResponseResult addRole(RoleDto roleDto);

    @ApiOperation("修改角色信息(关联权限)")
    public ResponseResult updateRole(RoleDto roleDto);



    @ApiOperation("删除角色信息(包括用户关联,权限关联)")
    public Result deleteRoleId(int[] roleIds);

    @ApiOperation("查询角色信息(包含分页,角色名称模糊匹配)")
    public Result findPageAndList(PageRoleName roleName);


    @ApiOperation("查询角色id和角色名称用户添加修改调用此接口")
    public Result findByRoleName();


    @ApiOperation("通过角色名称查询权限id和权限名称")
    public List<TbPermission> findRoleIdByPermissionIdAndPermissionName(int roleId);


}
