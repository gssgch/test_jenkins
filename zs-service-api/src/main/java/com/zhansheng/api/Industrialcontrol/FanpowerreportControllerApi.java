package com.zhansheng.api.Industrialcontrol;


import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.request.PowerrePort;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "发电量报表查询", description = "提供查询功能")
public interface FanpowerreportControllerApi {

    @ApiOperation("分页查询年/月发电量(发电量第一个输入框 ymd 1/年,2/月,/3日 接收int类型),(发电量第二个输入框为年/月 接收字符串类型)")
    public Result findByYMD(PowerrePort powerrePort, PageParam pageParam);


    @ApiOperation("Excel报表导出/年/月/日发电量")
    public Result findListExcel(PowerrePort powerrePort, HttpServletRequest request, HttpServletResponse response, int exportType);


}
