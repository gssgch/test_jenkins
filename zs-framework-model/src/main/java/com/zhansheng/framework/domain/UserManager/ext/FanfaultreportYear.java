package com.zhansheng.framework.domain.UserManager.ext;

import lombok.Data;

/**
 * author: Kwein
 * Date:2019/10/28
 * Time:10:52
 */
@Data
public class FanfaultreportYear {
    private Integer fanNum;

    private String year;

    private Integer num;

    public FanfaultreportYear(){}
    public FanfaultreportYear(Integer fanNum, String year,  Integer num)
    {
        this.fanNum = fanNum;
        this.year = year;
        this.num = num;
    }
}
