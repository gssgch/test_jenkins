package com.zhansheng.framework.domain.UserManager.ext;

import lombok.Data;

/**
 * author: Kwein
 * Date:2019/10/28
 * Time:10:52
 */
@Data
public class FanfaultreportMonth {
    private Integer fanNum;

    private String year;

    private String month;

    private Integer num;

    public FanfaultreportMonth(){}
    public FanfaultreportMonth(Integer fanNum, String year,String month,  Integer num)
    {
        this.fanNum = fanNum;
        this.year = year;
        this.month=month;
        this.num = num;
    }
}
