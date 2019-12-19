package com.zhansheng.api.Industrialcontrol;


import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmrules;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "告警策略管理接口", description = "告警策略管理接口，提供增、删、改、查方法")
public interface AlarmrulesControllerApi {


    @ApiOperation("添加告警处理信息")
    public ResponseResult addAlarmrules(TbAlarmrules alarmrules);

    @ApiOperation("查询告警处理信息")
    public Result findList(PageParam pageParam);

    @ApiOperation("删除告警处理信息")
    public ResponseResult deleteById(int id);

    @ApiOperation("修改告警处理信息")
    public ResponseResult updateAlarmrules(TbAlarmrules alarmrules);



}
