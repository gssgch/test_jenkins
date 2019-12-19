package com.zhansheng.framework.domain.UserManager;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TbWindmill {

    private Integer fWindmillId;
    private String fWindmillName;
    private String fCreateuser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fCreatetime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fUpdatetime;



}
