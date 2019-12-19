package com.zhansheng.api.Industrialcontrol;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanFaultRepair;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "添加检修管理接口", description = "添加检修接口提供增查方法")
public interface FanFaultRepairControllerApi {

    @ApiOperation("添加检修")
    public ResponseResult addFanFaultRepair(TbFanFaultRepair fanFaultRepair);

}
