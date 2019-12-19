package com.zhansheng.auth.service;



import com.zhansheng.framework.domain.UserManager.TbPermission;
import com.zhansheng.framework.domain.auth.Ext.ZsUserExt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    //本地拿到远程接口
    @Autowired
    private UserService userService;

    @Autowired
    ClientDetailsService clientDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if (authentication == null) {
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if (clientDetails != null) {
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        //从注册中心拿到接口  调用
        ZsUserExt userext = userService.getUserExt(username);
        if (userext == null) {
            //返回空给security 框架  用户名不存在
            return null;
        }

        //取出正确密码（hash值）
        String password = userext.getFPassword();
        //这里暂时使用静态密码
        //用户权限，这里暂时使用静态数据，最终会从数据库读取
        //从数据库获取权限
        List<TbPermission> permissions = userext.getPermissions();
        if(permissions == null){
            permissions = new ArrayList<>();
        }

        List<String> user_permission = new ArrayList<>();
        permissions.forEach(item-> user_permission.add(item.getFPermission()));
        String user_permission_string  = StringUtils.join(user_permission.toArray(), ",");

        UserJwt userDetails = new UserJwt(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));

        userDetails.setFUserId(userext.getFUserId());
        userDetails.setFUsername(userext.getFUsername());//用户名称

        return userDetails;
    }
}
