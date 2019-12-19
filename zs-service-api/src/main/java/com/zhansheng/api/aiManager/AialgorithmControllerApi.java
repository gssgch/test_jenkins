package com.zhansheng.api.aiManager;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.domain.aiManager.TbAIalgorithm;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;

@Api(value = "算法表管理接口（算法中心页面）", description = "算法表管理接口（算法中心页面）")
public interface AialgorithmControllerApi {

    @ApiOperation("添加")
    public ResponseResult addData(TbAIalgorithm aiData) throws IOException;

    @ApiOperation("查询（算法表）分页")
    public Result findList(PageParam pageParam, String algokey);

    @ApiOperation("查询（算法表）不分页")
    public Result findListBykey(String topic);

    @ApiOperation("删除")
    public ResponseResult deleteById(Integer Id);

    @ApiOperation("修改")
    public ResponseResult updateData(TbAIalgorithm aiData);

}
