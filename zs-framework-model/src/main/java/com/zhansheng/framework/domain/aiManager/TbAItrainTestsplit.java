package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 训练集测试集切分数据表
 *
 * @author zhansheng
 */
@Data
public class TbAItrainTestsplit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 专题
     */
    private String topic;

    /**
     * 特征选取的key
     */
    private String featureselkey;

    /**
     * 训练集编号,默认为空，测试集就1份，剩下的都是训练集
     */
    private String trainnum;

    /**
     * 测试集编号
     */
    private Integer testnum;

    /**
     * 总份数
     */
    private Integer shares;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 说明
     */
    private String descc;


}
