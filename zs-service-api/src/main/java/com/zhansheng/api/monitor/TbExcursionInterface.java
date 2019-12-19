package com.zhansheng.api.monitor;

import com.zhansheng.framework.domain.FaultDetection.CommonList;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.request.DraListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author
 * @description
 * @date 2019/6/26
 */
@Api(value = "风向偏移监控预测接口", description = "风向偏移监控预测接口，提供查询方法")
public interface TbExcursionInterface {

    @ApiOperation("分页查询环路风机列表")
    public Result findPageList(CommonList commonList);

    @ApiOperation("分页查询环路风机列表(新)")
    public Result findPageListNew(CommonList commonList);



    @ApiOperation("风向偏移整体状况趋势图(新 多条件查询!)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "draughtNumber", value = "风机编号", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "overTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "before", value = "滑动查询(前)", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "regret", value = "滑动查询(后)", required = false, paramType = "query", dataType = "int")
    })
    public Result findNumberListNew(DraListDto draListDto);





}