package com.zhansheng.framework.domain.UserManager.request;

import com.zhansheng.framework.domain.UserManager.TbUser;
import lombok.Data;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/5/22
 */
@Data
public class UserDto extends TbUser {
    private List<Integer> roleIds;


}
