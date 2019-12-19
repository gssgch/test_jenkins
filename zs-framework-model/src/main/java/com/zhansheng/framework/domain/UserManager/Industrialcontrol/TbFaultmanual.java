package com.zhansheng.framework.domain.UserManager.Industrialcontrol;

import lombok.Data;

@Data
public class TbFaultmanual {
    private Integer id;

    private String faulttype;

    private String sedleveltype;

    private String statuscode;

    private Integer serialnum;

    private String alarmnum;

    private String faultname;

    private String faultdesc;

    private String repair;

}