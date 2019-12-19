package com.zhansheng.framework.domain.UserManager.Industrialcontrol;

import lombok.Data;

@Data
public class TbAlarmrules {
    private Integer id;

    private Integer level;

    private String alarmConditionExp;

    private Integer fkFaultType;

    private Integer fkFaultLoc;

    private Integer fkAlarmreceiver;

    private String descb;

    private Integer alarmtype;


}