package com.zhansheng.framework.domain.UserManager.ext;


import lombok.Data;

@Data
public class FanpowerreportYear {

    private Integer fDraughtNumber;

    private String fYear;

    private Double fPower;


    public FanpowerreportYear(){}
    public FanpowerreportYear(Integer fDraughtNumber, String fYear, Double fPower) {
        this.fDraughtNumber = fDraughtNumber;
        this.fYear = fYear;
        this.fPower = fPower;
    }
}

