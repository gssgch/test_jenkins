package com.zhansheng.api.aiManager;

import com.zhansheng.framework.domain.Results.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Huige
 * Email: 824203453@qq.com
 * DATE: 2019/12/5
 * Desc:
 */
@Api(value = "模型可视化所有的key编号管理接口",description = "模型可视化所有key编号管理接口")
public interface AikeysnumControllerApi {

    @ApiOperation("查询所有数据")
     Result findList();

    @ApiOperation("根据key查询对应的编号")
    Result getNumByKey(String topickey);
}
