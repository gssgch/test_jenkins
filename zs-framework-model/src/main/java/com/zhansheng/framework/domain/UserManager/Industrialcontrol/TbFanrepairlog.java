package com.zhansheng.framework.domain.UserManager.Industrialcontrol;

import lombok.Data;

import java.util.Date;

@Data
public class TbFanrepairlog {
    private Integer id;

    private Date createtime;

    private Integer fannum;

    private String fault;

    private String faultrep;

    private Date repairtime;

   }