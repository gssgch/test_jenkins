package com.zhansheng.framework.domain.UserManager;

import lombok.Data;

import java.util.List;

@Data
public class TbLoop {




  private Integer fLoopId;
  private String fLoopName;

  private Integer totalPower;//有功功率
  private Integer draughtCount;//总风机数
  private Integer state_0;//正常
  private Integer state_1;//故障
  private Integer state_2;//停机
  private Integer state_3;
  private Double wind_spd;//30秒风速

  //一对多添加实体类
  private List<TbDraughtfan> tbDraughtList;

}
