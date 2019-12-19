package com.zhansheng.framework.domain.UserManager.response;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author
 * @description
 * @date 2019/4/29
 */
@Data
public class Role {

    private Integer fRoleId;
    private String fDeptname;
    private String fRolename;
    private String fMemo;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fCreatetime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fUpdatetime;

    private String fCreateuser;

}
