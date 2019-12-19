package com.zhansheng.framework.domain.UserManager.request;

import lombok.Data;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/12/7 11:04
 */
@Data
public class FaultmanualDto {
    private Integer page;
    private Integer size;
    private String faultname;

}
