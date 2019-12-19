package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.Serializable;

/**
 * 测试结果表
 *
 * @author zhansheng
 */
@Data
public class TbAItestResult implements Serializable {

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
     * 准确率
     */
    private Double accrate;

    /**
     * 召回率
     */
    private Double recallrate;

    /**
     * 误报率
     */
    private Double falsealarmrate;

    /**
     * 测试次数
     */
    private Integer testnum;

    /**
     * 测试步长
     */
    private Integer teststep;


}
