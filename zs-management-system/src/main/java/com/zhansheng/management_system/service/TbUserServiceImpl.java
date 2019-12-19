package com.zhansheng.management_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.cmsuser.TbUserInterface;

import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbRole;
import com.zhansheng.framework.domain.UserManager.TbUser;
import com.zhansheng.framework.domain.UserManager.request.PageUserName;
import com.zhansheng.framework.domain.UserManager.request.UserDto;
import com.zhansheng.management_system.dao.TbRoleMapper;
import com.zhansheng.management_system.dao.TbUserMapper;
import com.zhansheng.management_system.dao.TbUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
 * @date 2019/5/22
 */

@Service
@Transactional
public class TbUserServiceImpl implements TbUserInterface {

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private TbRoleMapper roleMapper;

    @Autowired
    private TbUserRoleMapper userRoleMapper;

    /**
     * 修改用户信息和对应的角色
     *
     * @param userDto
     * @return
     */
    @Override
    public Result updateUserAndRole(UserDto userDto) {
        if (userDto != null) {
            TbUser tbUser = userDto;
            tbUser.setFUpdatetime(new Date());
            int user = userMapper.updateUser(tbUser);
            saveUserRoles(userDto.getFUserId(), userDto.getRoleIds());
            if (user == 1) {
                return new Result(true, StatusCode.OK, "修改成功!");
            }
            return null;
        }
        return new Result(false, StatusCode.ERROR, "修改失败!");

    }

    /**
     * 添加用户信息以及中间表关联的角色
     *
     * @param userDto
     * @return
     */
    @Override
    public Result addUser(UserDto userDto) {
        if (userDto != null) {
            TbUser tbUser = userDto;
            //密码加密
            tbUser.setFPassword(new BCryptPasswordEncoder().encode(userDto.getFPassword()));
            tbUser.setFCreatetime(new Date());
            tbUser.setFUpdatetime(new Date());
            int addUser = userMapper.addUser(tbUser);
            //int i = 1 / 0;
            saveUserRoles(userDto.getFUserId(), userDto.getRoleIds());
            if (addUser == 1) {
                return new Result(true, StatusCode.OK, "添加成功!");
            }
            return null;
        }
        return new Result(false, StatusCode.ERROR, "添加失败!");

    }

    /**
     * 添加和修改保证数据同步
     *
     * @param userId
     * @param roleIds
     */
    private void saveUserRoles(Integer userId, List<Integer> roleIds) {
        //不管添加还是删除都会把userId]从中间表删除  删除完成之后再保存
        if (roleIds != null) {
            userRoleMapper.deleteUserAndRole(userId);
            if (!CollectionUtils.isEmpty(roleIds)) {
                userRoleMapper.addUserRoles(userId, roleIds);
            }
        }
    }

    /**
     * 添加的时候调用此方法 通过用户名判断用户是否存在
     *
     * @param username
     */
    public TbUser findOne(String username) {
        if (username != null && !username.equals("")) {
            TbUser user = userMapper.findOne(username);
            return user;
        }
        return null;
    }

    /**
     * 删除用户对应的角色
     *
     * @param userIds
     * @return
     */
    @Override
    public Result deleteUserIdAndRoleId(int[] userIds) {
        //删除用户表用户信息
        if (userIds.length > 0) {
            for (int userId : userIds) {
                int deleteId = userMapper.deleteId(userId);
                deleteRoleId(userIds);
            }
            return new Result(true, StatusCode.OK, "删除成功!");
        }
        return new Result(false, StatusCode.ERROR, "操作失败!");
    }

    /**
     * 删除中间表关联id
     *
     * @param userIds
     * @return
     */
    private void deleteRoleId(int[] userIds) {
        for (int userId : userIds) {
            //int i = 1 / 0;
            userRoleMapper.deleteUserAndRole(userId);
        }
    }

    /**
     * @param username    用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @Override
    public Result updatePassword(String username, String oldPassword, String newPassword) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        if (username.isEmpty()) {
            return new Result(false, StatusCode.ERROR, "参数不合法!");
        }
        if (oldPassword != null && !oldPassword.equals("")) {
            //通过当前用户名获取用户密码
            String password = userMapper.findUsernameByPassword(username);
            //给要修改的密码加密
            String hsahpassword = bcryptPasswordEncoder.encode(newPassword);
            //给传过来的密码当前密码加密
            String encode = bcryptPasswordEncoder.encode(oldPassword);
            //判断当前密码 和 数据库密码相同
            boolean b = bcryptPasswordEncoder.matches(oldPassword, password);
            //System.out.println(b);
            if (b == true) {//return new Result(false, StatusCode.ERROR, "旧密码输入有误,请再次输入!");
                //数据库密码和要修改的密码  做判断
                boolean f = bcryptPasswordEncoder.matches(newPassword, password);
                //System.out.println(f);
                if (f == true) {
                    return new Result(false, StatusCode.ERROR, "不能是近期使用过的密码，请重新输入!");
                }
                if (!hsahpassword.isEmpty()) {
                    int updatePassword = userMapper.updatePassword(username, hsahpassword);
                    if (updatePassword == 1) {
                        return new Result(true, StatusCode.OK, "修改密码成功!");
                    }
                    return new Result(false, StatusCode.ERROR, "修改失败,请联系管理员!");
                }
            }
            return new Result(false, StatusCode.ERROR, "旧密码输入有误,请再次输入!");
        }
        return new Result(false, StatusCode.ERROR, "请输入合法信息!");
    }

    /**
     * 查询所有记录数和用户名模糊匹配
     *
     * @param pageName     当前页码
     * @return
     */
    @Override
    public Result findPageAndList(PageUserName pageName) {
        if (pageName.getPage() <= 0) {
            pageName.setPage(1);
        }
        if (pageName.getSize() <= 0) {
            pageName.setSize(10);
        }
        //如果username是空就是没有模糊匹配  查询所有结果分页
        if (pageName.getUsername() == null || pageName.getUsername().equals("")) {
            PageHelper.startPage(pageName.getPage(), pageName.getSize());
            List<TbUser> list = userMapper.findList();
            PageInfo<TbUser> pageInfo = new PageInfo<TbUser>(list);
            Map<Object, Object> map = new HashMap<>();
            map.put("page", pageInfo.getList());
            map.put("size", pageInfo.getTotal());

            return new Result(true, StatusCode.OK, "查询所有用户信息!", map);
        }
        //如果username不是空  模糊匹配
        if (pageName.getUsername() != null && !pageName.getUsername().equals("")) {
            PageHelper.startPage(pageName.getPage(), pageName.getSize());
            List<TbUser> list = userMapper.findByPageUsername(pageName.getUsername());
            if (list.isEmpty()) {
                return null;
            }
            PageInfo<TbUser> pageInfo = new PageInfo<TbUser>(list);
            Map<Object, Object> map = new HashMap<>();
            map.put("page", pageInfo.getList());
            map.put("size", pageInfo.getTotal());
            return new Result(true, StatusCode.OK, "用户名模糊匹配结果!", map);
        }
        return new Result(false, StatusCode.REPERROR, "查询失败!");
    }

    /**
     * 通过用户Id查询角色名称角色ID
     *
     * @param userId
     * @return
     */
    @Override
    public List<TbRole> findUserIdByRoleIdAndRoleName(int userId) {
        return roleMapper.findUserIdByRoleIdAndRoleName(userId);
    }

}
