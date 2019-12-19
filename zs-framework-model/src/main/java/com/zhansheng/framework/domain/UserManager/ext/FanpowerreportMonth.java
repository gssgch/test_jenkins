package com.zhansheng.framework.domain.UserManager.ext;


import lombok.Data;

@Data
public class FanpowerreportMonth {

    private Integer fDraughtNumber;

    private String fYear;

    private String fMonth;

    private Double fPower;

    public FanpowerreportMonth(){}
    public FanpowerreportMonth(Integer fDraughtNumber, String fYear, String fMonth, Double fPower)
    {
        this.fDraughtNumber = fDraughtNumber;
        this.fYear = fYear;
        this.fMonth = fMonth;
        this.fPower = fPower;
    }
}