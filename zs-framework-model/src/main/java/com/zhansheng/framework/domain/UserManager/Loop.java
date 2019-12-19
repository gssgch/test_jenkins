package com.zhansheng.framework.domain.UserManager;

import lombok.Data;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/6/17
 */

@Data
public class Loop {


    private Integer fLoopId;
    private String fLoopName;

    private List<TbDraughtfan> tbDraughtfans;




}