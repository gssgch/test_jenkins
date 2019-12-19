package com.zhansheng.framework.domain.Results;

import com.zhansheng.framework.domain.UserManager.TbRole;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserPageResult extends ResponseResult {
    TbRole role;
    public UserPageResult(ResultCode resultCode, TbRole role) {
        super(resultCode);
        this.role = role;
    }
}
