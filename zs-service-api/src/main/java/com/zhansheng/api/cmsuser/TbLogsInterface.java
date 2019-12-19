package com.zhansheng.api.cmsuser;


import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbLog;
import com.zhansheng.framework.domain.UserManager.request.LogsParamList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "日志管理接口", description = "日志管理接口，提供查询方法")
public interface TbLogsInterface {

    @ApiOperation("分页查询日志列表")
    public Result findPageList(LogsParamList param);

    @ApiOperation("添加日志")
    public Result save(TbLog sysLogs);

}
