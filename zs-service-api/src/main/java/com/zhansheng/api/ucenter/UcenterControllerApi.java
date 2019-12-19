package com.zhansheng.api.ucenter;


import com.zhansheng.framework.domain.auth.Ext.ZsUserExt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户中心", description = "用户中心管理")
public interface UcenterControllerApi {


    @ApiOperation("根据用户账号查询用户信息")
    public ZsUserExt getUserext(String username);


}