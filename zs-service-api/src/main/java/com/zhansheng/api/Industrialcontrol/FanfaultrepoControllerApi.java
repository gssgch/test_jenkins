package com.zhansheng.api.Industrialcontrol;


import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanfaultrepo;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "告警故障知识库", description = "风机故障知识库")
public interface FanfaultrepoControllerApi {

    @ApiOperation("添加故障知识库")
    public ResponseResult addFanfaultrepo(TbFanfaultrepo fanfaultrepo);

    @ApiOperation("分页查询风机故障知识库")
    public Result findList(PageParam pageParam);

    @ApiOperation("删除风机故障知识库")
    public ResponseResult deleteById(int Id);

    @ApiOperation("修改风机故障知识库")
    public ResponseResult updateFanfaultrepo(TbFanfaultrepo fanfaultrepo);

}
