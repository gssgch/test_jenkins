package com.zhansheng.framework.domain.UserManager.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class LogsParamList {
    // 用户名字
    private String userName;
    // 操作类型
    private String module;
    // 状态
    private String flag;
    // 开始时间
    private String startTime;
    // 结束时间
    private String endTime;
    // 当前页码
    private Integer page;
    // 每页记录数
    private Integer size;

}
