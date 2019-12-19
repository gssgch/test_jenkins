package com.zhansheng.framework.domain.UserManager;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TbUser {

    private Integer fUserId;
    private String fUsername;
    private String fPassword;
    private String fTelepone;
    private String fEmail;
    private String fSex;

    private String fCreateuser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fCreatetime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fUpdatetime;

    private String fUserstories;



}
