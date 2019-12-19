package com.zhansheng.api.cmsowner;


import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbWindmill;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author
 * @description
 * @date 2019/6/14
 */
@Api(value = "风厂管理接口", description = "风厂管理接口,提供增删改查 方法")
public interface TbWindmillInterface {

    @ApiOperation("添加风厂")
    public Result addWindmill(TbWindmill windmill);

    @ApiOperation("删除风厂关联环路,风机")
    public Result deleteById(int windmillId);

    @ApiOperation("修改风厂信息")
    public Result updateByWindmill(TbWindmill windmill);

    @ApiOperation("查询风厂信息")
    public Result findList();

}
