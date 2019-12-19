package com.zhansheng.framework.domain.FaultDetection;

import lombok.Data;

/**
 * @author
 * @description
 * @date 2019/6/21
 */

@Data
public class CommonList {

    // 风厂ID
    private Integer windmillId;
    // 环路ID
    private Integer loopId;
    // 状态ID
    //private Integer stateCode;
    // 当前页码
    private Integer page;
    // 每页记录数
    private Integer size;

}
