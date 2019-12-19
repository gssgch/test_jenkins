package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.Serializable;

/**
 * 原始字段选取风机统计表
 *
 * @author zhansheng
 */
@Data
public class TbAIrawselectfanStat implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 原始字段选取表的key
     */
    private String selectkey;

    /**
     * 风机号
     */
    private String fannum;

    /**
     * 风机对应数据条数
     */
    private Integer counts;


}
