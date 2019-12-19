package com.zhansheng.api.aiManager;
import	java.awt.Desktop.Action;
import java.io.IOException;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbAIfeatureEngin;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "特征工程表管理接口（构造数据集页面）", description = "特征工程表管理接口（构造数据集页面）")
public interface AifeatureEnginControllerApi {

    @ApiOperation("添加")
    public Result addData(TbAIfeatureEngin aiData) throws IOException;

    @ApiOperation("查询（特征工程表）")
    public Result findList(String featurekey);

    @ApiOperation("通过专题查询（特征工程表）")
    public Result findListBytopic(String topic);

    @ApiOperation("查询（特征工程表风机统计表）")
    public Result findListFan(String featurekey, String selectkey);

    @ApiOperation("删除")
    public ResponseResult deleteById(Integer Id);

    @ApiOperation("修改")
    public ResponseResult updateData(TbAIfeatureEngin aiData);

}
