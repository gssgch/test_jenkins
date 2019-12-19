package com.zhansheng.framework.model.response;

import lombok.ToString;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 18:33.
 * @Modified By:
 */

@ToString
public enum CommonCode implements ResultCode{
    SUCCESS(true,10000,"操作成功！"),
    UNAUTHENTICATED(false,10001,"此操作需要登陆系统！"),
    UNAUTHORISE(false,10002,"权限不足，无权操作！"),
    INVALID_PARAM(false,10003,"非法参数！"),
    SERVER_SQL(false,10004,"插入数据库失败 ！"),
    SERVER_WINDMILL(false,10005,"风厂信息已存在不可添加风厂 ！"),
    SERVER_WINDMILL_NAME(false,10006,"风厂信息已存在,请核对风厂名称 ！"),
    SERVER_DRA_NUMBER(false,10007,"风机编号已存在,请检查风机编号 ！"),
    FAIL(false,11111,"操作失败！"),
    NOIMAGE(false,11112,"请选择图片！"),
    DELETE_ERRO(false,11113,"此数据被使用,不能执行该操作,若用疑问请联系管理员！"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功


    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;



    private CommonCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }
    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}
