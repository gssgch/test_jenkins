package com.zhansheng.api.aiManager;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbAIfeatureSelect;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "特征选取表管理接口（模型训练页面）", description = "特征选取表管理接口（模型训练页面）")
public interface AifeatureSelectControllerApi {

    @ApiOperation("添加")
    public Result addData(TbAIfeatureSelect aiData);

    @ApiOperation("查询（特征选取表）")
    public Result findList(String featureselkey);

    @ApiOperation("通过专题查询（特征选取表）")
    public Result findListBytopic(String topic);

    @ApiOperation("删除")
    public ResponseResult deleteById(Integer Id);

    @ApiOperation("修改")
    public ResponseResult updateData(TbAIfeatureSelect aiData);

}
