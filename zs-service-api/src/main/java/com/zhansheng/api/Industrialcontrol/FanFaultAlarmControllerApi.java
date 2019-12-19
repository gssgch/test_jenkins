package com.zhansheng.api.Industrialcontrol;


import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanfaultalarm;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultType;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "添加告警管理接口", description = "添加告警接口提供增查方法")
public interface FanFaultAlarmControllerApi {

    @ApiOperation("添加告警")
    public ResponseResult addFanfaultalarm(TbFanfaultalarm fanfaultalarm);

    @ApiOperation("查询告警,故障告警日志表")
    public Result findList();



}
