package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 模型预测参数表
 *
 * @author zhansheng
 */
@Data
public class TbAImodelpreparam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 模型预测参数的key
     */
    private String paramkey;

    /**
     * 预测的风机号
     */
    private String fannum;

    /**
     * 模型的key
     */
    private String modelkey;

    /**
     * 起始时间
     */
    private Date starttime;

    /**
     * 终止时间
     */
    private Date endtime;

    /**
     * 专题
     */
    private String topic;


}
