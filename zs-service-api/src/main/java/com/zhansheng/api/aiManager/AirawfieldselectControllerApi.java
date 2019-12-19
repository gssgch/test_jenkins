package com.zhansheng.api.aiManager;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbAIrawfieldselect;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "原始字段选取表管理接口（特征提取页面）", description = "原始字段选取表管理接口（特征提取页面）")
public interface AirawfieldselectControllerApi {

    @ApiOperation("添加")
    public Result addData(TbAIrawfieldselect aiData);

    @ApiOperation("查询（原始字段选取表）")
    public Result findList(String selectkey);

    @ApiOperation("查询（原始字段选取风机统计表）")
    public Result findListFan(String selectkey);

    @ApiOperation("删除")
    public ResponseResult deleteById(Integer Id);

    @ApiOperation("修改")
    public ResponseResult updateData(TbAIrawfieldselect aiData);

}
