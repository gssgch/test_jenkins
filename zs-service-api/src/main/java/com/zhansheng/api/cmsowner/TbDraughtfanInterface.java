package com.zhansheng.api.cmsowner;



import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbDraughtfan;
import com.zhansheng.framework.domain.UserManager.request.DraughtfanDto;
import com.zhansheng.framework.domain.UserManager.request.PageDraughtfan;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "风机管理接口", description = "风机管理接口,提供增删改查 方法")
public interface TbDraughtfanInterface {

    @ApiOperation("添加风机")
    public Result addDraughtfan(DraughtfanDto draughtfanDto);

    @ApiOperation("删除风机")
    public ResponseResult deleteById(int draughtfanId);

    @ApiOperation("修改风机信息")
    public Result updateDraughtfan(TbDraughtfan draughtfan);

    @ApiOperation("通过查询所属环路关联风机信息并分页")
    public Result findDraughtfanList(PageDraughtfan pageDraughtfan);

    @ApiOperation("查询所有风机号")
    public Result findDraughtfanNO();


}
