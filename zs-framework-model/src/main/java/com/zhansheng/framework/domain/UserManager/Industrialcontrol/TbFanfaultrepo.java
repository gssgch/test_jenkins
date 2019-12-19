package com.zhansheng.framework.domain.UserManager.Industrialcontrol;


import lombok.Data;

@Data
public class TbFanfaultrepo {
    private Integer id;

    private String statusCode;

    private Integer fkFaultType;

    private Integer fkFaultLoc;

    private Integer level;

    private String param1;

    private String param1Value;

    private String param2;

    private String param2Value;

    private String param3;

    private String param3Value;

    private String faultExp;

    private Integer frequence;

    private String repairProcess;

    private String descb;

}