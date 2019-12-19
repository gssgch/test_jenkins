package com.zhansheng.framework.domain.UserManager.Industrialcontrol;


import com.zhansheng.framework.domain.UserManager.ext.*;
import lombok.Data;

import java.util.HashMap;

@Data
public class TbFanfaultreport {

    private Integer fkFaultType;

    private Integer fkFaultLoc;

    private Integer fanNum;

    private Integer faultLevel;

    private String year;

    private String month;

    private String day;

    private Integer num;

    public TbFanfaultreport(){

    }

    public TbFanfaultreport(Integer fanNum, String year, Integer num) {
        this.fanNum = fanNum;
        this.year = year;
        this.day = day;
    }

    public TbFanfaultreport(Integer fanNum, String year, String month, Integer num) {
        this.fanNum = fanNum;
        this.year = year;
        this.month = month;
        this.num = num;
    }

    public TbFanfaultreport(Integer fanNum, String year, String month, String day, Integer num) {
        this.fanNum = fanNum;
        this.year = year;
        this.month = month;
        this.day = day;
        this.num = num;
    }
    public TbFanfaultreport(Integer fanNum, String year,Integer fkFaultLoc, Integer num) {
        this.fanNum = fanNum;
        this.year = year;
        this.fkFaultLoc=fkFaultLoc;
        this.num = num;
    }

    public TbFanfaultreport(Integer fanNum, String year, String month,Integer fkFaultLoc, Integer num) {
        this.fanNum = fanNum;
        this.year = year;
        this.month = month;
        this.fkFaultLoc=fkFaultLoc;
        this.num = num;
    }

    public TbFanfaultreport(Integer fanNum, String year, String month, String day,Integer fkFaultLoc, Integer num) {
        this.fanNum = fanNum;
        this.year = year;
        this.month = month;
        this.day = day;
        this.fkFaultLoc=fkFaultLoc;
        this.num = num;
    }

    public TbFanfaultreport( String year,Integer fanNum,Integer faultLevel, Integer num) {
        this.fanNum = fanNum;
        this.year = year;
        this.faultLevel=faultLevel;
        this.num = num;
    }

    public TbFanfaultreport(String year, String month,Integer fanNum,  Integer faultLevel, Integer num) {
        this.fanNum = fanNum;
        this.year = year;
        this.month = month;
        this.faultLevel=faultLevel;
        this.num = num;
    }

    public TbFanfaultreport(String year,String month, String day, Integer fanNum, Integer faultLevel, Integer num) {
        this.fanNum = fanNum;
        this.year = year;
        this.month = month;
        this.day = day;
        this.faultLevel=faultLevel;
        this.num = num;
    }
    /*
    *
    * 转换
    * */
    //每年故障总数
    public FanfaultreportYear transyear(TbFanfaultreport tb){
        return new FanfaultreportYear(tb.getFanNum(),tb.getYear(),tb.getNum());
}
    //每月故障总数
    public FanfaultreportMonth transmonth(TbFanfaultreport tb){
        return new FanfaultreportMonth(tb.getFanNum(),tb.getYear(),tb.getMonth(),tb.getNum());
    }
    //每天故障总数
    public FanfaultreportDay transday(TbFanfaultreport tb){
        return new FanfaultreportDay(tb.getFanNum(),tb.getYear(),tb.getMonth(),tb.getDay(),tb.num);
    }
    //每年故障位置
    public FanfaultLocYear translocyear(TbFanfaultreport tb, HashMap<Integer,String> mp){
        return new FanfaultLocYear(tb.getFanNum(),tb.getYear(),mp.getOrDefault(tb.fkFaultLoc,"未知故障"),tb.getNum());
    }
    //每月故障位置
    public FanfaultLocMonth translocmonth(TbFanfaultreport tb, HashMap<Integer,String> mp){
        return new FanfaultLocMonth(tb.getFanNum(),tb.getYear(),tb.getMonth(),mp.getOrDefault(tb.fkFaultLoc,"未知故障"),tb.getNum());
    }
    //每天故障位置
    public FanfaultLocDay translocday(TbFanfaultreport tb, HashMap<Integer,String> mp){
        return new FanfaultLocDay(tb.getFanNum(),tb.getYear(),tb.getMonth(),tb.getDay(),mp.getOrDefault(tb.fkFaultLoc,"未知故障"),tb.getNum());
    }
    //每年故障等级
    public FanfaultLevelYear translevelyear(TbFanfaultreport tb, HashMap<Integer,String> mp){
        return new FanfaultLevelYear(tb.getFanNum(),tb.getYear(),mp.getOrDefault(tb.faultLevel,"未知级别"),tb.getNum());
    }
    //每月故障等级
    public FanfaultLevelMonth translevelmonth(TbFanfaultreport tb, HashMap<Integer,String> mp){
        return new FanfaultLevelMonth(tb.getFanNum(),tb.getYear(),tb.getMonth(),mp.getOrDefault(tb.faultLevel,"未知级别"),tb.getNum());
    }
    //每天故障等级
    public FanfaultLevelDay translevelday(TbFanfaultreport tb,HashMap<Integer,String> mp){
        return new FanfaultLevelDay(tb.getFanNum(),tb.getYear(),tb.getMonth(),tb.getDay(),mp.getOrDefault(tb.faultLevel,"未知级别"),tb.getNum());
    }





}