package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 特征选取表
 *
 * @author zhansheng
 */
@Data
public class TbAIfeatureSelect implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 特征选取key，关联字段
     */
    private String featureselkey;

    /**
     * 多选风机号，用逗号分割
     */
    private String fannums;

    /**
     * 专题
     */
    private String topic;

    /**
     * 特征工程的key
     */
    private String featurekey;

    /**
     * 特征选取数据存储路径
     */
    private String datapath;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 特征工程的数据份数
     */
    private Integer shares;

    /**
     * 查询区间的起始时间
     */
    private Date starttime;

    /**
     * 查询区间的终止时间
     */
    private Date endtime;

    /**
     * 查询的所有字段,用逗号分割
     */
    private String selcolumns;


}
