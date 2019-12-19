package com.zhansheng.framework.domain.UserManager.response;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Power {

  private Integer fPowerId;
  private String fCreateuser;
  private String fUpdateuser;
  private Integer fRighttype;
  private Integer fPageid;
  private Integer fMenuId;
  private String fPoername;
  private String fPath;
  private Integer fBoarid;
  private String fMemo;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date fUpdatetime;



}
