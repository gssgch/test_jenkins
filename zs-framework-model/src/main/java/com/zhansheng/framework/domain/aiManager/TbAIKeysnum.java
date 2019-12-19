package com.zhansheng.framework.domain.aiManager;

import lombok.Data;

import java.io.Serializable;

/**
 * 自增表
 *
 * @author zhansheng
 */
@Data
public class TbAIKeysnum implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 当前值
     */
    private String curnum;


}
