package com.zhansheng.framework.domain.UserManager.ext;


import lombok.Data;

@Data
public class Fanpowerreport {

    private Integer id;

    private Integer fDraughtNumber;

    private String fYear;

    private String fMonth;

    private String fDay;

    private Double fPower;

    public Fanpowerreport(){

    }

    public Fanpowerreport(Integer fDraughtNumber, String fYear, Double fPower) {
        this.fDraughtNumber = fDraughtNumber;
        this.fYear = fYear;
        this.fPower = fPower;
    }

    public Fanpowerreport(Integer fDraughtNumber, String fYear, String fMonth, Double fPower) {
        this.fDraughtNumber = fDraughtNumber;
        this.fYear = fYear;
        this.fMonth = fMonth;
        this.fPower = fPower;
    }

    public Fanpowerreport(Integer fDraughtNumber, String fYear, String fMonth, String fDay, Double fPower) {
        this.fDraughtNumber = fDraughtNumber;
        this.fYear = fYear;
        this.fMonth = fMonth;
        this.fDay = fDay;
        this.fPower = fPower;
    }

    //
    public FanpowerreportYear transyear(Fanpowerreport tb){
        return new FanpowerreportYear(tb.getFDraughtNumber(),tb.getFYear(),tb.getFPower());
    }

    //
    public FanpowerreportMonth transmonth(Fanpowerreport tb){
        return new FanpowerreportMonth(tb.getFDraughtNumber(),tb.getFYear(),tb.getFMonth(),tb.getFPower());
    }

    public FanpowerreportDay transday(Fanpowerreport tb){
        return new FanpowerreportDay(tb.getFDraughtNumber(),tb.getFYear(),tb.getFMonth(),tb.getFDay(),tb.getFPower());
    }
}


