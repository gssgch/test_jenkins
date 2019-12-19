package com.zhansheng.api.Industrialcontrol;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFault;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "故障中心管理接口", description = "故障中心接口提供增删改查方法")
public interface FanFaultControllerApi {

    @ApiOperation("添加故障（风机故障表）")
    public ResponseResult addFanFault(TbFault fault);

    @ApiOperation("分页查询故障中心（风机故障表）")
    public Result findList(PageParam pageParam, Integer faultFrom);

    @ApiOperation("删除故障中心（风机故障表）")
    public ResponseResult deleteById(int Id);

    @ApiOperation("修改故障中心（风机故障表）")
    public ResponseResult updateFanFault(TbFault fault);

}
