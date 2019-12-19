package com.zhansheng.api.monitor;

import com.zhansheng.framework.domain.FaultDetection.CommonList;
import com.zhansheng.framework.domain.Results.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author
 * @description
 * @date 2019/6/26
 */

@Api(value = "风机劣化监控预测接口", description = "风机劣化监控预测接口，提供查询方法")
public interface TbDegradationInterface {


    /*
    *
    * 所有mapper  映射文件  可参考   TbDegradationMapper
    *
    * */


    /*@ApiOperation("分页查询环路风机列表")
    public Result findPageList(CommonList commonList);

    @ApiOperation("风机劣化状态趋势图")
    public Result findBydraughtNumber(int draughtNumber);

    @ApiOperation("风机劣化整体状况趋势图")
    public Result findBydraughtNumberList(int draughtNumber);*/



}
