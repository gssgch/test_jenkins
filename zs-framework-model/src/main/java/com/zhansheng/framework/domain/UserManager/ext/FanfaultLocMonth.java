package com.zhansheng.framework.domain.UserManager.ext;

import lombok.Data;

/**
 * author: Kwein
 * Date:2019/10/25
 * Time:17:01
 */
@Data
public class FanfaultLocMonth{

    private Integer fanNum;

    private String year;

    private String month;

    private String fkFaultLoc;

    private int num;

    public FanfaultLocMonth(){}
    public FanfaultLocMonth(Integer fanNum, String year, String month, String fkFaultLoc,Integer num)
    {
        this.fanNum = fanNum;
        this.year = year;
        this.month = month;
        this.fkFaultLoc = fkFaultLoc;
        this.num=num;
    }
}