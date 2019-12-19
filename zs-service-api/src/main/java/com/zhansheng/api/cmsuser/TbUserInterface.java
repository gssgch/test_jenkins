package com.zhansheng.api.cmsuser;



import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbRole;
import com.zhansheng.framework.domain.UserManager.request.PageUserName;
import com.zhansheng.framework.domain.UserManager.request.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;


@Api(value = "用户管理接口", description = "用户管理接口，提供增、删、改、查方法")
public interface TbUserInterface {


    @ApiOperation("删除用户 角色信息(删除的是中间表数据)")
    public Result deleteUserIdAndRoleId(int[] userId);

    @ApiOperation("添加用户及对应的角色(账户类型)")
    public Result addUser(UserDto userDto);

    @ApiOperation("修改用户信息和角色")
    public Result updateUserAndRole(UserDto userDto);

    @ApiOperation("通过用户名修改密码") @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "oldPassword", value = "当前密码", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, paramType = "path", dataType = "String")
    })
    public Result updatePassword(String username, String oldPassword, String newPassword);


    @ApiOperation("查询用户信息(包含分页,用户名模糊匹配)")
    public Result findPageAndList(PageUserName userName);

    @ApiOperation("通过用户Id查询角色名称角色ID")
    public List<TbRole> findUserIdByRoleIdAndRoleName (int userId);



}
