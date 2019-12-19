package com.zhansheng.framework.domain.UserManager;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class TbLog {

  private Integer fLogId;
  private String fUserName;
  private String fModule;
  private String fFlag;
  private String fRemark;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Date fCreateTime;
}
