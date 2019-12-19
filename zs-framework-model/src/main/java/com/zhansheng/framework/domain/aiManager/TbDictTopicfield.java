package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典表
 *
 * @author zhansheng
 */
@Data
public class TbDictTopicfield implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Integer fDictid;

    /**
     * 字典类型
     */
    private String fDicttype;

    /**
     * key
     */
    private String fDictkey;

    /**
     * 字典值
     */
    private String fDictvalue;


}
