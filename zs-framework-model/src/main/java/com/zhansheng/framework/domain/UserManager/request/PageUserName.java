package com.zhansheng.framework.domain.UserManager.request;

import lombok.Data;

/**
 * @author
 * @description
 * @date 2019/5/27
 */
@Data
public class PageUserName {

    private Integer page;
    private Integer size;
    private String username;



}
