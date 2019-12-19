package com.zhansheng.management_system.controller;

import com.zhansheng.api.cmsuser.TbUserInterface;

import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbRole;
import com.zhansheng.framework.domain.UserManager.TbUser;
import com.zhansheng.framework.domain.UserManager.request.PageUserName;
import com.zhansheng.framework.domain.UserManager.request.UserDto;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbUserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author
 * @description
 * @date 2019/5/21
 */

@RestController
@RequestMapping("/User")
public class UserController implements TbUserInterface {

    @Autowired
    private TbUserServiceImpl userService;



    //当用户拥有user_delete权限时候方可访问此方法
    @Override
    @LogAnnotation
    @ApiOperation(value = "删除用户")
    @PreAuthorize("hasAuthority('user_management')")
    @DeleteMapping("/deleteUserIdAndRoleId")
    public Result deleteUserIdAndRoleId(@RequestParam(value = "userIds") int[] userIds) {
        return userService.deleteUserIdAndRoleId(userIds);
    }

    //当用户拥有user_find_list权限时候方可访问此方法
    @Override
//    @PreAuthorize("hasAuthority('user_management')")
    @PostMapping("/findByPageUsernameAndList")
    public Result findPageAndList(@RequestBody PageUserName userName) {
        return userService.findPageAndList(userName);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "修改用户")
    @PreAuthorize("hasAuthority('user_management')")
    @PostMapping("/updateUserAndRole")
    public Result updateUserAndRole(@RequestBody UserDto userDto) {
        return userService.updateUserAndRole(userDto);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "添加用户")
    @PreAuthorize("hasAuthority('user_management')")
    @PostMapping("/addUserAndRole")
    public Result addUser(@RequestBody UserDto userDto) {
        String username = userDto.getFUsername();
        TbUser tbuser = userService.findOne(username);
        if (tbuser != null){
            return new Result(false, StatusCode.ERROR,"用户名已存在!");
        }
        return userService.addUser(userDto);
    }

    /**
     * 修改密码接口
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    @LogAnnotation
    @ApiOperation(value = "修改密码")
    @PreAuthorize("hasAuthority('user_update_password_management')")
    @PostMapping("/updatePassword/{username}/{oldPassword}/{newPassword}")
    public Result updatePassword(@PathVariable("username") String username,@PathVariable("oldPassword") String oldPassword,@PathVariable("newPassword") String newPassword) {
       return userService.updatePassword(username,oldPassword,newPassword);
    }

    @Override
    @GetMapping("/findUserIdByRoleIdAndRoleName/{userId}")
    public List<TbRole> findUserIdByRoleIdAndRoleName(@PathVariable("userId") int userId) {
        return userService.findUserIdByRoleIdAndRoleName(userId);
    }

}
