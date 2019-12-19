package com.zhansheng.framework.domain.UserManager.response;

import lombok.Data;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/12/4 14:06
 */

@Data
public class WindmillCount {

    private Integer state_0;
    private Integer state_1;
    private Integer state_2;
    private Integer count;
    private Integer totalPower;
    private Double windSpd;

}
