package com.zhansheng.framework.domain.UserManager.ext;

import lombok.Data;

/**
 * author: Kwein
 * Date:2019/10/25
 * Time:17:02
 */
@Data
public class FanfaultLevelMonth{

    private Integer fanNum;

    private String year;

    private String month;
    //故障级别
    private String faultLevel;

    private int num;

    public FanfaultLevelMonth(){}
    public FanfaultLevelMonth(Integer fanNum, String year, String month, String faultLevel,Integer num)
    {
        this.fanNum = fanNum;
        this.year = year;
        this.month = month;
        this.faultLevel = faultLevel;
        this.num=num;
    }
}