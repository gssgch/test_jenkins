package com.zhansheng.framework.domain.UserManager.request;

import com.zhansheng.framework.domain.UserManager.Loop;
import com.zhansheng.framework.domain.UserManager.TbLoop;
import lombok.Data;


/**
 * @author
 * @description
 * @date 2019/6/14
 */
@Data
public class LoopDto extends Loop {

    private Integer windmillId;

}
