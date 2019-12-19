package com.zhansheng.framework.domain.UserManager.Industrialcontrol;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TbFanFaultRepair {
    private Integer id;

    private Integer fkFanfaultId;

    private String repairName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date repairDate;

    private Integer status;

    private String faultExp;

    private String faultDesc;

    private String repairAnswer;

    private String descb;

    private String statusCode;
}