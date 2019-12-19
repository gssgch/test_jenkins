package com.zhansheng.framework.domain.FaultDetection;

import lombok.Data;

@Data
public class ModelsParamList {
    // 风厂ID
    private int windmillId;
    // 各问题模型Id
    private int code;

}
