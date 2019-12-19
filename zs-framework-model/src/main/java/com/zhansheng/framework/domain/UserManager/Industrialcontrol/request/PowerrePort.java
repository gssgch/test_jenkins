package com.zhansheng.framework.domain.UserManager.Industrialcontrol.request;

import lombok.Data;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/10/16 9:38
 */

@Data
public class PowerrePort {

    //年
    private String fyear;
    //月
    private String fmonth;

    //年 / 月 / 日
    private int ymd;

    //风机编号
    private int number;

}
