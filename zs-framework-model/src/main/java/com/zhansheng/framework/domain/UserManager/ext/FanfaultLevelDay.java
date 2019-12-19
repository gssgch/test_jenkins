package com.zhansheng.framework.domain.UserManager.ext;

import lombok.Data;

/**
 * author: Kwein
 * Date:2019/10/25
 * Time:17:02
 */
@Data
public class FanfaultLevelDay{

    private Integer fanNum;

    private String year;

    private String month;

    private String day;

    private String faultLevel;

    private int num;

    public FanfaultLevelDay(){}
    public FanfaultLevelDay(Integer fanNum, String year, String month,String day, String faultLevel,Integer num)
    {
        this.fanNum = fanNum;
        this.year = year;
        this.month = month;
        this.day = day;
        this.faultLevel = faultLevel;
        this.num=num;
    }
}