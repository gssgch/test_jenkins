package com.zhansheng.api.auth;


import com.zhansheng.framework.domain.auth.response.JwtResult;
import com.zhansheng.framework.domain.auth.response.LoginResult;
import com.zhansheng.framework.domain.auth.resquest.LoginRequest;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author
 * @description
 * @date 2019/6/4
 */

@Api(value = "用户认证", description = "用户认证接口")
public interface AuthControllerInterface {

    @ApiOperation("登录")
    public LoginResult login(LoginRequest loginRequest);

    @ApiOperation("退出")
    public ResponseResult logout();

    @ApiOperation("查询用户jwt令牌")
    public JwtResult userjwt();

}
