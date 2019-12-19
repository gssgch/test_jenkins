package com.zhansheng.framework.domain.FaultDetection;

import lombok.Data;

@Data
public class TotalDto {

  private Integer fLoopId;
  private Integer totalPower;
  private Integer draughtCount;
  private Integer state_0;
  private Integer state_1;
  private Integer state_2;
  private Integer state_3;
  private float wind_spd;

}
