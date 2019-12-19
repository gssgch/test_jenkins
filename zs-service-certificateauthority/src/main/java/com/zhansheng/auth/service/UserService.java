package com.zhansheng.auth.service;


import com.zhansheng.auth.dao.TbPermissionMapper;
import com.zhansheng.auth.dao.ZsUserMapper;
import com.zhansheng.framework.domain.UserManager.TbPermission;
import com.zhansheng.framework.domain.UserManager.TbUser;
import com.zhansheng.framework.domain.auth.Ext.ZsUserExt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/6/4
 */
@Service
public class UserService {

    @Autowired
    private TbPermissionMapper permissionMapper;

    @Autowired
    private ZsUserMapper userMapper;

    //根据账号查询xcUser信息
    public TbUser findUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }


    public ZsUserExt getUserExt(String username) {
        TbUser user = this.findUserByUsername(username);
        if (user == null) {
            return null;
        }

        Integer userId = user.getFUserId();
        List<TbPermission> permissions = permissionMapper.selectPermissionByUserId(userId);
        ZsUserExt zsUserExt = new ZsUserExt();
        BeanUtils.copyProperties(user, zsUserExt);
        //用户的权限
        zsUserExt.setPermissions(permissions);
        return zsUserExt;
    }


}
