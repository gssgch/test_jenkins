package com.zhansheng.framework.domain.Results;

import com.zhansheng.framework.model.response.ResultCode;
import lombok.ToString;

/**
 * Created by mrt on 2018/3/5.
 */
@ToString
public enum UserCode implements ResultCode {

    USER_ADDPAGE_EXISTSNAME(false,24001,"角色名称已存在！"),
    USER_ADDPAGE_EXCEL(false,24002,"文件名不是excel格式！");


    //操作代码
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private UserCode(boolean success, int code, String message){
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
