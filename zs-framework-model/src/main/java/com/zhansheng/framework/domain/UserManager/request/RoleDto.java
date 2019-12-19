package com.zhansheng.framework.domain.UserManager.request;

import com.zhansheng.framework.domain.UserManager.TbRole;
import lombok.Data;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/5/24
 */

@Data
public class RoleDto extends TbRole {

    private List<Integer> permissionIds;

}
