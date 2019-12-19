package com.zhansheng.api.cmsowner;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Loop;
import com.zhansheng.framework.domain.UserManager.request.LoopDto;
import com.zhansheng.framework.domain.UserManager.request.PageLoop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "环路管理接口", description = "环路管理接口,提供增删改查 方法")
public interface TbLoopInterface {

    @ApiOperation("添加环路")
    public Result addLoop(LoopDto loopDto);

    @ApiOperation("删除环路关联风机")
    public Result deleteById(int loopId);

    @ApiOperation("修改环路信息")
    public Result updateLoop(Loop loop);

    @ApiOperation("通过风厂信息查询所属环路信息")
    public Result findWindmillIdByLoopList(PageLoop pageLoop);

}