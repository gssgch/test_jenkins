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
@Api(value = "各主题下的数据类型管理接口",description = "各主题数据类型管理接口")
public interface DictTopicfieldControllerApi {

    @ApiOperation("查询所有数据")
    public Result findByTopic(int topic);
}
