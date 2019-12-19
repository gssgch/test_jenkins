package com.zhansheng.api.aiManager;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbAItrainTestsplit;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "训练集测试集切分数据表管理接口", description = "训练集测试集切分数据表管理接口")
public interface AitrainTestsplitControllerApi {

    @ApiOperation("添加")
    public Result addData(TbAItrainTestsplit aiData);

    @ApiOperation("查询（训练集测试集切分数据表）")
    public Result findList(String featureselkey);

    @ApiOperation("删除")
    public ResponseResult deleteById(Integer Id);

    @ApiOperation("修改")
    public ResponseResult updateData(TbAItrainTestsplit aiData);

}
