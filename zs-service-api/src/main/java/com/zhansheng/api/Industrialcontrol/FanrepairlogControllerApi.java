package com.zhansheng.api.Industrialcontrol;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanrepairlog;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/12/5 18:50
 */
@Api(value = "检修记录接口", description = "检修记录接口提供增删改查方法")
public interface FanrepairlogControllerApi {

    @ApiOperation("添加检修手册")
    public ResponseResult addFanrepairlog(TbFanrepairlog fanrepairlog);


    @ApiOperation("修改检修手册")
    public ResponseResult updateFanrepairlog(TbFanrepairlog fanrepairlog);


    @ApiOperation("删除检修手册")
    public ResponseResult deleteById(int id);


    @ApiOperation("分页查询检修手册")
    public Result findByPageList(PageParam pageParam);


}
