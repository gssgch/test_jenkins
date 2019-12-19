package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.Serializable;

/**
 * 训练结果表
 *
 * @author zhansheng
 */
@Data
public class TbAItrainResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 模型的key
     */
    private String modelkey;

    /**
     * loss
     */
    private Double loss;

    /**
     * 训练次数
     */
    private Integer trainnum;

    /**
     * 训练步长
     */
    private Integer trainstep;


}
