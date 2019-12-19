package com.zhansheng.framework.domain.UserManager;


import com.zhansheng.framework.domain.FaultDetection.TbParticulars;
import com.zhansheng.framework.domain.FaultDetection.TbState;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbParticularsMysql;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class TbDraughtfan {

    private Integer fDraughtId;
    private Integer fDraughtNumber;
    private String fUpdateuser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fCreatetime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fUpdatetime;

    //百分比
    private Double percent;
    //状态
    private Integer state;


    private TbState tbState;
    private TbStateMysql tbState1;

    private TbParticulars tbParticulars;
    private TbParticularsMysql tbParticulars1;

    private List<TbDraughtfan> mapFan;

}
