package com.zhansheng.framework.domain.UserManager.Industrialcontrol;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TbFault {
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fanDate;

    private String statusCode;

    private Integer faultLevel;

    private Integer fkFaultType;

    private Integer fkFaultLoc;

    private Integer fanNum;

    private String faultLocPic;

    private String param1;

    private String param1Value;

    private String param2;

    private String param2Value;

    private String param3;

    private String param3Value;

    private Integer faultFrom;

    private Integer status;

    private String descb;

    private Integer alarmStatus;

    private TbFanFaultRepair fanFaultRepair;

    private TbFanfaultalarm fanFaultAlarm;

    private TbFaultLoc faultLoc;
    private TbFaultType faultType;


}