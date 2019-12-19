package com.zhansheng.api.monitor;


import com.zhansheng.framework.domain.FaultDetection.CommonList;
import com.zhansheng.framework.domain.Results.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "首页查询接口", description = "首页查询接口，提供查询方法")
public interface TbIndexInterface {



    /*@ApiOperation("首页查询")
    public Result findPageList(int windmillId);*/


    @ApiOperation("机组总览查询")
    public Result findPageListNew(int windmillId);

    @ApiOperation("模型查询")
    public Result findModel(int number);


}
