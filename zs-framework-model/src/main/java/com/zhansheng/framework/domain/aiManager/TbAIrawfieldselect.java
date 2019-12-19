package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 原始字段选取表
 *
 * @author zhansheng
 */
@Data
public class TbAIrawfieldselect implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 原始字段选取的key，关联字段
     */
    private String selectkey;

    /**
     * 专题，每一个算法课题
     */
    private String topic;

    /**
     * 多选风机号，各个编号之间用逗号分割
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
     * 查询的所有字段,用逗号分割
     */
    private String selcolumns;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 该条件生成数据在文件中的存储位置
     */
    private String datapath;


}
