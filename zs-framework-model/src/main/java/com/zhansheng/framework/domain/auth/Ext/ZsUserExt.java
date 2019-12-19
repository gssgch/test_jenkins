package com.zhansheng.framework.domain.auth.Ext;

import com.zhansheng.framework.domain.UserManager.TbPermission;
import com.zhansheng.framework.domain.UserManager.TbUser;
import lombok.Data;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/6/3
 */
@Data
public class ZsUserExt extends TbUser {


    //权限信息
    private List<TbPermission> permissions;


}
