package com.zhansheng.api.Industrialcontrol;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmreceiver;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanfaultrepo;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/9/27 17:21
 */
@Api(value = "告警处理接口" , description = "告警处理接口提供增删改查方法")
public interface AlarmreceiverControllerApi {

    @ApiOperation("添加告警处理信息")
    public ResponseResult addAlarmreceiver(TbAlarmreceiver alarmreceiver);

    @ApiOperation("查询告警处理信息")
    public Result findList(PageParam pageParam);

    @ApiOperation("删除告警处理信息")
    public ResponseResult deleteById(int id);

    @ApiOperation("修改告警处理信息")
    public ResponseResult updateAlarmreceiver(TbAlarmreceiver alarmreceiver);



}
