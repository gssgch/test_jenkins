package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.File;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 算法表
 *
 * @author zhansheng
 */
@Data
public class TbAIalgorithm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 算法编号
     */
    private String algokey;

    /**
     * 算法名称
     */
    private String algoname;

    /**
     * 专题
     */
    private String topic;

    /**
     * 算法文件存储路径
     */
    private String shellpath;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 是否禁用，0是不可以，1是可以
     */
    private String available;

    /**
     * 算法说明
     */
    private String descc;

    private String coding;


}
