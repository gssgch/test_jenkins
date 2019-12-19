package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 模型表
 *
 * @author zhansheng
 */
@Data
public class TbAImodel implements Serializable {

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
     * 模型对应的专题
     */
    private String topic;

    /**
     * 特征选取的key
     */
    private String featureselkey;

    /**
     * 特征选取的key对应的数据份数
     */
    private Integer shares;

    /**
     * 算法的key
     */
    private String algokey;

    /**
     * 测试集编号
     */
    private Integer testdatanum;

    /**
     * 迭代次数
     */
    private Integer iternum;

    /**
     * 模型存储路径
     */
    private String modelpath;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 是否禁用，0是不可以，1是可以
     */
    private Integer available;

    /**
     * 模型说明
     */
    private String descc;


}
