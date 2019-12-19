package com.zhansheng.framework.domain.UserManager.ext;

import lombok.Data;

/**
 * author: Kwein
 * Date:2019/10/25
 * Time:17:01
 */
@Data
public class FanfaultLocYear{

    private Integer fanNum;

    private String year;

    private String fkFaultLoc;

    private int num;

    public FanfaultLocYear(){}
    public FanfaultLocYear(Integer fanNum, String year, String fkFaultLoc,Integer num)
    {
        this.fanNum = fanNum;
        this.year = year;
        this.fkFaultLoc = fkFaultLoc;
        this.num=num;
    }
}