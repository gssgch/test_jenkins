package com.zhansheng.framework.domain.UserManager;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TbStateMysql {

    private Integer fId;
    private Integer fDraughtNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date fCreateTime;
    //工控
    private Integer fState1Value;
    //叶片
    private Integer fState2Value;
    //振动
    private Integer fState3Value;

    private int f;
}
