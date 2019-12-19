package com.zhansheng.management_system.controller;

import com.zhansheng.api.cmsuser.TbRoleInterface;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.Results.UserCode;
import com.zhansheng.framework.domain.UserManager.TbPermission;
import com.zhansheng.framework.domain.UserManager.TbRole;
import com.zhansheng.framework.domain.UserManager.request.PageRoleName;
import com.zhansheng.framework.domain.UserManager.request.RoleDto;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.ResponseResult;

import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbRoleServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author
 * @description
 * @date 2019/5/27
 */

@RestController
@RequestMapping("/Role")
public class RoleController implements TbRoleInterface {

    @Autowired
    private TbRoleServiceImpl roleService;

    @Override
    @LogAnnotation
    @ApiOperation(value = "修改角色")
    @PreAuthorize("hasAuthority('loop_management')")
    @PostMapping("/updateRole")
    public ResponseResult updateRole(@RequestBody RoleDto roleDto) {
        return roleService.updateRole(roleDto);
    }

    /**
     * 添加角色
     * @param roleDto
     * @return
     */
    @Override
    @LogAnnotation
    @ApiOperation(value = "添加角色")
    @PreAuthorize("hasAuthority('loop_management')")
    @PostMapping("/addRole")
    public ResponseResult addRole(@RequestBody RoleDto roleDto) {
        String rolename = roleDto.getFRolename();
        TbRole roleName = roleService.findRoleNameByRoleName(rolename);
        if (roleName != null ){
            ExceptionCast.cast(UserCode.USER_ADDPAGE_EXISTSNAME);
        }
        return roleService.addRole(roleDto);
    }


    /*@Override
    //@PreAuthorize("hasAuthority('user_delete')")
    @PostMapping("/saveRole")
    public Result saveRole(@RequestBody RoleDto roleDto) {
        return roleService.saveRole(roleDto);

    }*/

    @Override
    @LogAnnotation
    @ApiOperation(value = "删除角色")
    @PreAuthorize("hasAuthority('loop_management')")
    @DeleteMapping("/deleteRoleId")
    public Result deleteRoleId(@RequestParam("roleIds") int[] roleIds) {
        return roleService.deleteRoleId(roleIds);
    }


    //查询所有权限信息
    @Override
    @PreAuthorize("hasAuthority('loop_management')")
    @PostMapping("/findPageAndList")
    public Result findPageAndList(@RequestBody PageRoleName roleName) {
        return roleService.findPageAndList(roleName);
    }

    @Override
    @PostMapping("/findByRoleName")
    public Result findByRoleName() {
        return roleService.findByRoleName();
    }

    //添加修改权限
    @Override
    @GetMapping("/findRoleIdByPermissionIdAndPermissionName/{roleId}")
    public List<TbPermission> findRoleIdByPermissionIdAndPermissionName(@PathVariable("roleId") int roleId) {
        return roleService.findRoleIdByPermissionIdAndPermissionName(roleId);
    }

}
