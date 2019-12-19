package com.zhansheng.api.base;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(value = "Excel管理接口", description = "Excel管理接口，提供导入,导出方法")
public interface ExcelControllerApi {

    @ApiOperation("查询每日发电量Excel报表导出")
    void findByDay(@RequestParam Map<String, String> map, HttpServletRequest request, HttpServletResponse response);


}
