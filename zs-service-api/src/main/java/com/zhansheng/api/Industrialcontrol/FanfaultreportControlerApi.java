package com.zhansheng.api.Industrialcontrol;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.request.PowerrePort;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "风机故障统计报表", description = "风机故障统计报表")
public interface  FanfaultreportControlerApi {

    @ApiOperation("分页查询风机故障总数")
    public Result findByYMD(PowerrePort powerrePort, PageParam pageParam);

    @ApiOperation("分页查询风机故障位置")
    public Result findByloc(PowerrePort powerrePort, PageParam pageParam);

    @ApiOperation("分页查询风机故障等级")
    public Result findBylevel(PowerrePort powerrePort, PageParam pageParam);
}
