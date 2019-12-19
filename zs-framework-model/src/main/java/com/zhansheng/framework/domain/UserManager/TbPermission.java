package com.zhansheng.framework.domain.UserManager;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class TbPermission {

    private Integer fPermissionId;
    private Integer fParentId;
    private String fPoername;
    private String fPermission;
    private String fCreateuser;
    private String fUpdateuser;
    private Integer fType;
    private Integer fSort;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fUpdatetime;

    //递归查询添加权限信息
    private List<TbPermission> child;


}
