package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.Serializable;

/**
 * 模型预测结果表
 *
 * @author zhansheng
 */
@Data
public class TbAImodelpreresult implements Serializable {

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
     * 概率
     */
    private Double prob;

    /**
     * 阈值
     */
    private Double thresholdvalue;

    /**
     * 分类结果
     */
    private Integer result;


}
