package com.zhansheng.framework.domain.UserManager.Industrialcontrol;

import lombok.Data;

import java.util.Date;
@Data
public class TbFanfaultalarm {
    private Integer id;

    private Date alarmDate;

    private Integer alarmLevel;

    private String receiver;

    private String phone;

    private String mail;

    private Integer status;

    private Integer alarmtype;

    private Integer fkFanfaultId;
}