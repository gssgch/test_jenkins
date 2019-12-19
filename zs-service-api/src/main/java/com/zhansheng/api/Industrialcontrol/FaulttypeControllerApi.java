package com.zhansheng.api.Industrialcontrol;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmreceiver;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultType;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;


@Api(value = "告警类型接口" , description = "告警类型接口提供增删改查方法")
public interface FaulttypeControllerApi {


    @ApiOperation("添加告警类型信息")
    public ResponseResult addFaultType(TbFaultType faultType);

    @ApiOperation("查询告警类型信息")
    public Result findList(PageParam pageParam);

    @ApiOperation("删除告警类型信息")
    public ResponseResult deleteById(Integer id);

    @ApiOperation("修改告警类型信息")
    public ResponseResult updateFaultType(TbFaultType faultType);


}
