package com.zhansheng.api.base;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmreceiver;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbDictKey;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultLoc;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/9/29 14:05
 */
@Api(value = "base管理接口", description = "base管理接口，提供查询方法")
public interface BaseControllerApi {

    @ApiOperation("获取告警类型")
    List<TbFaultType> findType();

    @ApiOperation("获取告警位置")
    List<TbFaultLoc> findLoc();

    @ApiOperation("获取告警处理人")
    List<TbAlarmreceiver> findAlar();

    @ApiOperation("查询key")
    List<TbDictKey> findByKey(String key);

    @ApiOperation("模型可视化查询各表的key")
     String findAINumByKey(String topicKey);


}
