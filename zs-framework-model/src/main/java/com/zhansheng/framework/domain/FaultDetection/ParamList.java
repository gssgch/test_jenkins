package com.zhansheng.framework.domain.FaultDetection;

import lombok.Data;

@Data
public class ParamList {
    // 风厂ID
    private String windmillId;
    // 环路ID
    private String loopId;
    // 状态ID
    private Integer stateCode;
    // 当前页码
    private Integer page;
    // 每页记录数
    private Integer size;

}
