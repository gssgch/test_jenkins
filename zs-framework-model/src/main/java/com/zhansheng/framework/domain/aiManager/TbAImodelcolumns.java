package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字段类
 *
 * @author zhansheng
 */
@Data
public class TbAImodelcolumns implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模型的key
     */
    private String modelkey;

    /**
     * 迭代次数
     */
    private Integer iternum;

    /**
     * 测试集编号
     */
    private Integer testdatanum;

    /**
     * 模型说明
     */
    private String modeldescc;

    /**
     * 算法名称
     */
    private String algoname;

    /**
     * 算法文件存储路径
     */
    private String shellpath;

    /**
     * 算法说明
     */
    private String sfdescc;

    /**
     * 是否禁用，0是不可以，1是可以
     */
    private String available;

    /**
     * 多选风机号，用逗号分割
     */
    private String fannums;

    /**
     * 查询区间的起始时间
     */
    private Date starttime;

    /**
     * 查询区间的终止时间
     */
    private Date endtime;

    /**
     * 特征选取数据存储路径
     */
    private String datapath;

    /**
     * 特征工程的数据份数
     */
    private Integer shares;

    /**
     * 提取的特征字段
     */
    private String featureColumns;

    /**
     * 特征工程脚本路径
     */
    private String featureshellpath;

    /**
     * 查询的所有字段,用逗号分割
     */
    private String selcolumns;

}
