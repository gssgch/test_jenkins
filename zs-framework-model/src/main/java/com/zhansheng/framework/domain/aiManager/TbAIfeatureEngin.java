package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 特征工程表
 *
 * @author zhansheng
 */
@Data
public class TbAIfeatureEngin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 特征工程的key，关联字段
     */
    private String featurekey;

    /**
     * 专题，每一个算法课题
     */
    private String topic;

    /**
     * 提取的特征字段
     */
    private String featureColumns;

    /**
     * 异常数量
     */
    private Integer expnum;

    /**
     * 数据的形状，行和列用逗号分割，,eg：行,列   
     */
    private String datashape;

    /**
     * 原始数据选取的key
     */
    private String selectkey;

    /**
     * 特征工程脚本路径
     */
    private String shellpath;

    /**
     * 特征工程数据存储路径
     */
    private String datapath;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 多选风机号，各个编号之间用逗号分割
     */
    private String fannums;

    /**
     * python代码
     */
    private String coding;


}
