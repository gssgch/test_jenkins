package com.zhansheng.framework.domain.UserManager.ext;

import lombok.Data;

/**
 * author: Kwein
 * Date:2019/10/28
 * Time:10:53
 */
@Data
public class FanfaultreportDay {
    private Integer fanNum;

    private String year;

    private String month;

    private String day;

    private Integer num;

    public FanfaultreportDay(){}
    public FanfaultreportDay(Integer fanNum, String year,String month,String day,  Integer num)
    {
        this.fanNum = fanNum;
        this.year = year;
        this.month=month;
        this.day=day;
        this.num = num;
    }
}
