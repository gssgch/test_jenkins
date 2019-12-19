package com.zhansheng.api.aiManager;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbAImodel;
import com.zhansheng.framework.domain.aiManager.TbAImodelpreparam;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "模型表管理接口", description = "模型表管理接口")
public interface AimodelControllerApi {

    @ApiOperation("添加")
    public ResponseResult addData(TbAImodel aiData);

    @ApiOperation("添加模型预测参数表")
    public Result addDatamodelpreparam(TbAImodelpreparam aiData);

    @ApiOperation("查询（模型表）")
    public Result findList(String modelkey);

    @ApiOperation("通过专题，特征选取，算法key查询（模型表）")
    public Result findListByThreeKey(String topic, String featureselkey, String algokey);

    @ApiOperation("查询（模型表15字段）")
    public Result findColumns(String modelkey);

    @ApiOperation("通过专题查询（模型表）")
    public Result findListByKey(String topic);

    @ApiOperation("删除")
    public ResponseResult deleteById(Integer Id);

    @ApiOperation("修改")
    public ResponseResult updateData(TbAImodel aiData);

    @ApiOperation("查询（测试结果表）")
    public Result findListTestResult(String modelkey);

    @ApiOperation("查询（训练结果表）")
    public Result findListTrainResult(String modelkey);

    @ApiOperation("查询（模型预测参数表）")
    public Result findListModelpreparam(String paramkey);

    @ApiOperation("查询（模型预测结果表）")
    public Result findListModelpreresult(String paramkey);

}
