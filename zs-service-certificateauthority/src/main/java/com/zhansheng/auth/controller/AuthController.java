package com.zhansheng.auth.controller;

import com.zhansheng.api.auth.AuthControllerInterface;
import com.zhansheng.auth.client.UserClient;
import com.zhansheng.auth.service.AuthService;

import com.zhansheng.framework.domain.auth.Ext.AuthToken;
import com.zhansheng.framework.domain.auth.response.AuthCode;
import com.zhansheng.framework.domain.auth.response.JwtResult;
import com.zhansheng.framework.domain.auth.response.LoginResult;
import com.zhansheng.framework.domain.auth.resquest.LoginRequest;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/")
public class AuthController implements AuthControllerInterface {

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    String user;

    @Autowired
    AuthService authService;

    @Autowired
    UserClient userClient;


    @Override
    @PostMapping("/userlogin")
    public LoginResult login( LoginRequest loginRequest) {
        if (loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())) {
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }
        if (loginRequest == null || StringUtils.isEmpty(loginRequest.getPassword())) {
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }
        //账号
        String username = loginRequest.getUsername();
        user = username;
        //密码
        String password = loginRequest.getPassword();
        //申请令牌
        AuthToken authToken = authService.login(username, password, clientId, clientSecret);
        //用户身份令牌
        String access_token = authToken.getAccess_token();
        //将令牌存储到cookie
        this.saveCookie(access_token);

        //日志存储
//        userClient.saveLogin(username, "登录", "1", null);
        return new LoginResult(CommonCode.SUCCESS, access_token);
    }


    /**
     * 将令牌存储到cookie
     *
     * @param token
     */
    private void saveCookie(String token) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, cookieMaxAge, false);
    }


    /**
     * 从cookie拿到uid  通过uid查询redis令牌
     *
     * @return
     */
    @Override
    @GetMapping("/userjwt")
    public JwtResult userjwt() {
        //取出cookie中的用户身份令牌
        String uid = getTokenFormCookie();
        if (uid == null) {
            return new JwtResult(CommonCode.FAIL, null);
        }
        //拿身份令牌从redis中查询jwt令牌
        AuthToken userToken = authService.getUserToken(uid);
        if (userToken != null) {
            //将jwt令牌返回给用户
            String jwt_token = userToken.getJwt_token();
            return new JwtResult(CommonCode.SUCCESS, jwt_token);
        }
        return null;
    }


    /**
     * 取出cookie中的身份令牌
     *
     * @return
     */
    private String getTokenFormCookie() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> map = CookieUtil.readCookie(request, "uid");
        if (map != null && map.get("uid") != null) {
            String uid = map.get("uid");
            return uid;
        }
        return null;
    }



    /**
     * 退出接口
     *
     * @return
     */
    @Override
    @PostMapping("/userlogout")
    public ResponseResult logout() {
        //通过cookir查询uid
        String uid = getTokenFormCookie();
        //日志存储
//        userClient.saveLogin(user, "退出", "1", null);
        //删除redis
        authService.delToken(uid);
        //删除cookie
        this.clearCookie(uid);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 从cookie删除token
     *
     * @param token
     */
    private void clearCookie(String token) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, 0, false);
    }


}
