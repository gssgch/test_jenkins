package com.zhansheng.api.model;


import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFault;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.request.CommonListI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "工控系统首页接口", description = "工控系统首页接口")
public interface IndustrialIndexControllerApi {


    @ApiOperation("故障记录数")
    public int findFaultCount();

    @ApiOperation("查询故障详细信息")
    public List<TbFault> findFault();

    @ApiOperation("查询工控系统风机详细信息(code模型状态 1工控,2叶片,3振动)")
    public Result findByState(CommonListI commonList);


}
