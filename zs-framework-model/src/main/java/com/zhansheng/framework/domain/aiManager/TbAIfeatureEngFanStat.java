package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.Serializable;

/**
 * 特征工程表风机统计表
 *
 * @author zhansheng
 */
@Data
public class TbAIfeatureEngFanStat implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 特征工程表的key
     */
    private String featurekey;

    /**
     * 风机号
     */
    private String fannum;

    /**
     * 风机对应数据条数
     */
    private Integer counts;


}
