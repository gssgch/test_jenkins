package com.zhansheng.framework.domain.UserManager.ext;

import lombok.Data;

/**
 * author: Kwein
 * Date:2019/10/25
 * Time:16:02
 */
@Data
public class FanpowerreportDay {

    private Integer fDraughtNumber;

    private String fYear;

    private String fMonth;

    private String fday;

    private Double fPower;

    public FanpowerreportDay(){}
    public FanpowerreportDay(Integer fDraughtNumber, String fYear, String fMonth,String fday, Double fPower)
    {
        this.fDraughtNumber = fDraughtNumber;
        this.fYear = fYear;
        this.fMonth = fMonth;
        this.fday = fday;
        this.fPower = fPower;
    }
}