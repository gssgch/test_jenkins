package com.zhansheng.framework.domain.UserManager.ext;

import lombok.Data;

/**
 * author: Kwein
 * Date:2019/10/25
 * Time:17:02
 */
@Data
public class FanfaultLevelYear{

    private Integer fanNum;

    private String year;

    private String faultLevel;

    private int num;

    public FanfaultLevelYear(){}
    public FanfaultLevelYear(Integer fanNum, String year, String faultLevel,Integer num)
    {
        this.fanNum = fanNum;
        this.year = year;
        this.faultLevel = faultLevel;
        this.num=num;
    }
}