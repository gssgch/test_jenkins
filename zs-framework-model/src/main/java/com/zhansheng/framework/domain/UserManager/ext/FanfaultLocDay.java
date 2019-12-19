package com.zhansheng.framework.domain.UserManager.ext;

import lombok.Data;

/**
 * author: Kwein
 * Date:2019/10/25
 * Time:17:01
 */
@Data
public class FanfaultLocDay{

    private Integer fanNum;

    private String year;

    private String month;

    private String day;

    private String fkFaultLoc;

    private int num;

    public FanfaultLocDay(){}
    public FanfaultLocDay(Integer fanNum, String year, String month,String day, String fkFaultLoc,Integer num)
    {
        this.fanNum = fanNum;
        this.year = year;
        this.month = month;
        this.day = day;
        this.fkFaultLoc = fkFaultLoc;
        this.num=num;
    }
}