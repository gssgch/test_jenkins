package com.zhansheng.api.monitor;


import com.zhansheng.framework.domain.FaultDetection.CommonList;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.request.DraListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "叶片结冰监控预测接口", description = "叶片结冰监控预测接口，提供查询方法")
public interface BladeInterface {

    @ApiOperation("分页查询环路风机列表")
    public Result findPageList(CommonList commonList);


    @ApiOperation("分页查询环路风机列表(新)")
    public Result findPageListNew(CommonList commonList);

    @ApiOperation("叶片结冰整体状况趋势图(新)")
    public Result findBydraughtNumberListNew(int draughtNumber);

    @ApiOperation("叶片结冰整体状况趋势图(新 多条件查询!)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "draughtNumber", value = "风机编号", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "overTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "before", value = "滑动查询(前)", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "regret", value = "滑动查询(后)", required = false, paramType = "query", dataType = "int")
    })
    public Result findNumberListNew(DraListDto draListDto);


    /*@ApiOperation("叶片结冰状态(百分比)趋势图")
    public Result findBydraughtNumber(int draughtNumber);*/

    @ApiOperation("叶片结冰整体状况趋势图")
    public Result findBydraughtNumberList(int draughtNumber);


}
