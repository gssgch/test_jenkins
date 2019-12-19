package com.zhansheng.framework.domain.UserManager.request;

import lombok.Data;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/12/5 12:01
 */

@Data
public class DraListDto {

    private Integer draughtNumber;//风机编号
    private String startTime;//开始时间
    private String overTime;//结束时间
    private Integer before;//前
    private Integer regret;//后

}
