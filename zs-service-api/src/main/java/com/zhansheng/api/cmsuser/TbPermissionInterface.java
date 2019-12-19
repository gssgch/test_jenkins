package com.zhansheng.api.cmsuser;


import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "权限管理接口", description = "查询权限信息")
public interface TbPermissionInterface {

    @ApiOperation("权限树")
    public JSONArray permissionsAll();



}
