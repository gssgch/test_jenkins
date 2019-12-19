package com.zhansheng.management_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.cmsuser.TbRoleInterface;

import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.Results.UserPageResult;
import com.zhansheng.framework.domain.UserManager.TbPermission;
import com.zhansheng.framework.domain.UserManager.TbRole;
import com.zhansheng.framework.domain.UserManager.request.PageRoleName;
import com.zhansheng.framework.domain.UserManager.request.RoleDto;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;

import com.zhansheng.management_system.dao.TbPermissionMapper;
import com.zhansheng.management_system.dao.TbRoleMapper;
import com.zhansheng.management_system.dao.TbRolePermissionMapper;
import com.zhansheng.management_system.dao.TbUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @description
 * @date 2019/5/27
 */

@Service
@Transactional
public class TbRoleServiceImpl implements TbRoleInterface {

    @Autowired
    private TbRoleMapper roleMapper;

    @Autowired
    private TbRolePermissionMapper rolePermissionMapper;

    @Autowired
    private TbUserRoleMapper userRoleMapper;

    @Autowired
    private TbPermissionMapper permissionMapper;

    //用户管理权限  增删改方法  必须默认添加查询方法
    private void user(List<Integer> permissionIds) {
        try {
            //user管理权限
            int userAdd = 3;
            int userDelete = 4;
            int userUpdate = 5;
            int userfind = 6;
            for (Integer userId : permissionIds) {
                if (userId == userAdd || userId == userDelete || userId == userUpdate) {
                    permissionIds.add(userfind);
                }
            }
        } catch (Exception e) {
        }
    }

    //角色管理权限  增删改方法  必须默认添加查询方法
    private void role(List<Integer> permissionIds) {
        try {
            int roleAdd = 8;
            int roleDelete = 9;
            int roleUpdate = 10;
            int rolefind = 11;
            for (Integer userId : permissionIds) {
                if (userId == roleAdd || userId == roleDelete || userId == roleUpdate) {
                    permissionIds.add(rolefind);
                }
            }
        } catch (Exception e) {
        }
    }

    //风厂管理权限  增删改方法  必须默认添加查询方法
    private void windmill(List<Integer> permissionIds) {
        try {
            int windmillAdd = 13;
            int windmillDelete = 16;
            int windmillUpdate = 14;
            int windmillfind = 15;
            for (Integer userId : permissionIds) {
                if (userId == windmillAdd || userId == windmillDelete || userId == windmillUpdate) {
                    permissionIds.add(windmillfind);
                }
            }
        } catch (Exception e) {
        }
    }

    //环路管理权限  增删改方法  必须默认添加查询方法
    private void loop(List<Integer> permissionIds) {
        try {
            int loopAdd = 18;
            int loopDelete = 19;
            int loopUpdate = 20;
            int loopfind = 21;
            //风厂查询
            int windmillfind = 15;
            for (Integer userId : permissionIds) {
                if (userId == loopAdd || userId == loopDelete || userId == loopfind) {
                    permissionIds.add(loopfind);
                    permissionIds.add(windmillfind);

                }
            }
        } catch (Exception e) {
        }
    }

    //风机管理权限  增删改方法  必须默认添加查询方法
    private void dra(List<Integer> permissionIds) {
        int draughtfanAdd = 23;
        int draughtfanDelete = 24;
        int draughtfanUpdate = 25;
        int draughtfanFind = 26;
        //环路查询
        int loopFind = 21;
        //风厂查询
        int windmillFind = 15;
        try {
            for (Integer id : permissionIds) {
                if (id == draughtfanAdd || id == draughtfanDelete || id == draughtfanUpdate) {
                    permissionIds.add(windmillFind);
                    permissionIds.add(loopFind);
                    permissionIds.add(draughtfanFind);

                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * 添加角色
     *
     * @param roleDto
     * @return
     */
    @Override
    public ResponseResult addRole(RoleDto roleDto) {
        TbRole role = roleDto;
        List<Integer> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0L);
        TbRole tbRole = role;
        tbRole.setFCreatetime(new Date());
        tbRole.setFUpdatetime(new Date());
        /*//风机管理权限  增删改方法  必须默认添加查询方法
        dra(permissionIds);
        //用户管理权限  增删改方法  必须默认添加查询方法
        user(permissionIds);
        //角色管理权限  增删改方法  必须默认添加查询方法
        role(permissionIds);
        //风厂管理权限  增删改方法  必须默认添加查询方法
        windmill(permissionIds);
        //环路管理权限  增删改方法  必须默认添加查询方法
        loop(permissionIds);
        //向角色表添加数据*/
        int addRole = roleMapper.addRole(tbRole);
        //int i = 1 / 0;
        if (!CollectionUtils.isEmpty(permissionIds)) {
            //向中间表添加数据
            rolePermissionMapper.addRolePermission(role.getFRoleId(), permissionIds);
        }
        return new UserPageResult(CommonCode.SUCCESS,null);
    }

    /**
     * 修改角色
     *
     * @param roleDto
     * @return
     */
    @Override
    public ResponseResult updateRole(RoleDto roleDto) {
        TbRole role = roleDto;
        List<Integer> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0L);
        TbRole tbRole = role;
        tbRole.setFUpdatetime(new Date());
       /* //风机管理权限  增删改方法  必须默认添加查询方法
        dra(permissionIds);
        //用户管理权限  增删改方法  必须默认添加查询方法
        user(permissionIds);
        //角色管理权限  增删改方法  必须默认添加查询方法
        role(permissionIds);
        //风厂管理权限  增删改方法  必须默认添加查询方法
        windmill(permissionIds);
        //环路管理权限  增删改方法  必须默认添加查询方法
        loop(permissionIds);*/

        int updateRole = roleMapper.updateRole(tbRole);
        //int i = 1 / 0;
        int permission = rolePermissionMapper.deleteRolePermission(role.getFRoleId());
        if (!CollectionUtils.isEmpty(permissionIds)) {
            rolePermissionMapper.addRolePermission(role.getFRoleId(), permissionIds);
        }
        return new UserPageResult(CommonCode.SUCCESS, null);
    }

    /**
     * 如果删除这个角色 会从user role permission 三张表删除对应的关联id
     *
     * @param
     * @return
     */
    @Override
    public Result deleteRoleId(int[] roleIds) {
        if (roleIds.length > 0) {
            deleteByRoleId(roleIds);
            deleteByRoleIdAndUserId(roleIds);
            deleteByRoleIdAndPermissionId(roleIds);
            return new Result(true, StatusCode.OK, "删除成功!");
        }
        return new Result(false, StatusCode.ERROR, "操作失败!");
    }

    /**
     * 删除角色关联用户id
     *
     * @param roleIds
     */
    private void deleteByRoleIdAndUserId(int[] roleIds) {
        for (int roleId : roleIds) {
            userRoleMapper.deleteByRoleIdAndUserId(roleId);
        }
    }

    /**
     * 删除角色关联权限id
     *
     * @param roleIds
     */
    private void deleteByRoleId(int[] roleIds) {
        for (int roleId : roleIds) {
            roleMapper.deleteByRoleId(roleId);
        }
    }

    /**
     * 删除权限表关联id
     *
     * @param roleIds
     */
    private void deleteByRoleIdAndPermissionId(int[] roleIds) {
        for (int roleId : roleIds) {
            rolePermissionMapper.deleteRolePermission(roleId);
        }
    }


    /**
     * 查询角色所有信息 (模糊查询角色名称)
     *
     * @param roleName
     * @return
     */
    @Override
    public Result findPageAndList(PageRoleName roleName) {
        if (roleName.getPage() <= 0) {
            roleName.setPage(1);
        }
        if (roleName.getSize() <= 0) {
            roleName.setSize(10);
        }
        //如果username是空就是没有模糊匹配  查询所有结果分页
        if (roleName.getRolename() == null || roleName.getRolename().equals("")) {
            PageHelper.startPage(roleName.getPage(), roleName.getSize());
            List<TbRole> roleList = roleMapper.findRoleList();
            PageInfo<TbRole> pageInfo = new PageInfo<TbRole>(roleList);
            Map<Object, Object> map = new HashMap<>();
            map.put("page", pageInfo.getList());
            map.put("size", pageInfo.getTotal());
            return new Result(true, StatusCode.OK, "查询所有用户信息!", map);
        }
        //如果username不是空  模糊匹配
        if (roleName.getRolename() != null && !roleName.getRolename().equals("")) {
            PageHelper.startPage(roleName.getPage(), roleName.getSize());
            List<TbRole> list = roleMapper.findPageAndList(roleName.getRolename());
            if (list.isEmpty()) {
                return null;
            }
            PageInfo<TbRole> pageInfo = new PageInfo<TbRole>(list);
            Map<Object, Object> map = new HashMap<>();
            map.put("page", pageInfo.getList());
            map.put("size", pageInfo.getTotal());
            return new Result(true, StatusCode.OK, "用户名模糊匹配结果!", map);
        }
        return new Result(false, StatusCode.ERROR, "查询失败!");
    }

    /**
     * 查询角色名称和角色ID
     *
     * @return
     */
    @Override
    public Result findByRoleName() {
        List<TbRole> list = roleMapper.findByRoleName();
        if (list != null) {
            return new Result(true, StatusCode.OK, "查询成功!", list);
        }
        return new Result(false, StatusCode.ERROR, "查询失败!");
    }

    /**
     * 通过角色id查询权限名称权限id
     *
     * @param roleId
     * @return
     */
    @Override
    public List<TbPermission> findRoleIdByPermissionIdAndPermissionName(int roleId) {
        return permissionMapper.findRoleIdByPermissionIdAndPermissionName(roleId);
    }

    public TbRole findRoleNameByRoleName(String roleName) {
        return roleMapper.findRoleNameByRoleName(roleName);
    }

}
