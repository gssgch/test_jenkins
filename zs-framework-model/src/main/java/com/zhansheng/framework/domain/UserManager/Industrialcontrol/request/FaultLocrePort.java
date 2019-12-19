package com.zhansheng.framework.domain.UserManager.Industrialcontrol.request;

import lombok.Data;

/**
 * author: Kwein
 * Date:2019/10/21
 * Time:16:10
 */
@Data
public class FaultLocrePort {
    //风机编号
    private int fanNum;
    //年 / 月 / 日
    private int ymd;
    //月
    private String month;
    //年
    private String year;
    //故障位置
    private int fkFaultLoc;



}
