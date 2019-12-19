package com.zhansheng.framework.domain.UserManager;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TbRole {

  private Integer fRoleId;
  private String fRolename;
  private String fMemo;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date fCreatetime;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date fUpdatetime;

  private String fCreateuser;

}
