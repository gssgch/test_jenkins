package com.zhansheng.framework.domain.UserManager.request;

import lombok.Data;

/**
 * @author
 * @description
 * @date 2019/5/27
 */

@Data
public class PageRoleName {

    private Integer page;
    private Integer size;
    private String rolename;

}
