package com.zhansheng.framework.domain.UserManager.Industrialcontrol;

import com.zhansheng.framework.domain.UserManager.ext.FanpowerreportDay;
import com.zhansheng.framework.domain.UserManager.ext.FanpowerreportMonth;
import com.zhansheng.framework.domain.UserManager.ext.FanpowerreportYear;
import lombok.Data;

@Data
public class TbFanpowerreport {

    private Integer id;

    private Integer fDraughtNumber;

    private String fYear;

    private String fMonth;

    private String fDay;

    private Double fPower;

    public TbFanpowerreport(){

    }

    public TbFanpowerreport(Integer fDraughtNumber, String fYear, Double fPower) {
        this.fDraughtNumber = fDraughtNumber;
        this.fYear = fYear;
        this.fPower = fPower;
    }

    public TbFanpowerreport(Integer fDraughtNumber, String fYear, String fMonth, Double fPower) {
        this.fDraughtNumber = fDraughtNumber;
        this.fYear = fYear;
        this.fMonth = fMonth;
        this.fPower = fPower;
    }

    public TbFanpowerreport(Integer fDraughtNumber, String fYear, String fMonth, String fDay, Double fPower) {
        this.fDraughtNumber = fDraughtNumber;
        this.fYear = fYear;
        this.fMonth = fMonth;
        this.fDay = fDay;
        this.fPower = fPower;
    }

    //
    public FanpowerreportYear transyear(TbFanpowerreport tb){
        return new FanpowerreportYear(tb.getFDraughtNumber(),tb.getFYear(),tb.getFPower());
    }

    //
    public FanpowerreportMonth transmonth(TbFanpowerreport tb){
        return new FanpowerreportMonth(tb.getFDraughtNumber(),tb.getFYear(),tb.getFMonth(),tb.getFPower());
    }
    public FanpowerreportDay transday(TbFanpowerreport tb){
        return new FanpowerreportDay(tb.getFDraughtNumber(),tb.getFYear(),tb.getFMonth(),tb.getFDay(),tb.getFPower());
    }


}