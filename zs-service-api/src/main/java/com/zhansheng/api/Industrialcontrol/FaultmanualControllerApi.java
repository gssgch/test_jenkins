package com.zhansheng.api.Industrialcontrol;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultmanual;
import com.zhansheng.framework.domain.UserManager.request.FaultmanualDto;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/12/5 18:50
 */
@Api(value = "故障手册表接口", description = "故障手册表接口提供增删改查方法")
public interface FaultmanualControllerApi {

    @ApiOperation("添加检修手册")
    public ResponseResult addFaultmanual(TbFaultmanual faultmanual);


    @ApiOperation("修改检修手册")
    public ResponseResult updateFaultmanual(TbFaultmanual faultmanual);


    @ApiOperation("删除检修手册")
    public ResponseResult deleteById(int id);


    @ApiOperation("分页查询检修手册")
    public Result findByPageList(FaultmanualDto pageParam);

}
