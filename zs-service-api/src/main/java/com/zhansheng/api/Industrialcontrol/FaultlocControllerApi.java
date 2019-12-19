package com.zhansheng.api.Industrialcontrol;


import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultLoc;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "告警位置接口", description = "告警位置接口提供增删改查方法")
public interface FaultlocControllerApi {


    @ApiOperation("添加告警位置信息")
    public ResponseResult addFaultLoc(HttpServletRequest request,
                                      HttpServletResponse response,
                                      TbFaultLoc faultLoc,
                                      MultipartFile file) throws IOException;


    @ApiOperation("删除位置处理信息")
    public ResponseResult deleteById(int id);

    @ApiOperation("修改位置处理信息")
    public ResponseResult updateFaultLoc(HttpServletRequest request,
                                         HttpServletResponse response,
                                         TbFaultLoc faultLoc,
                                         MultipartFile file);

    @ApiOperation("分页查询处理信息")
    public Result findList(PageParam pageParam);


}
