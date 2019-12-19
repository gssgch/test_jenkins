package com.zhansheng.framework.domain.Results;

/**
 * @author
 * @description
 * @date 2019/4/1
 */

public class ResultException {
    private boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public ResultException() {
    }

    public ResultException(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultException(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;

        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }




}
