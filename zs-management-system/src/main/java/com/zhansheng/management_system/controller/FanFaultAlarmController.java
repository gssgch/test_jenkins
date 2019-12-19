package com.zhansheng.management_system.controller;

import com.zhansheng.api.Industrialcontrol.FanFaultAlarmControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanfaultalarm;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbFanFaultAlarmServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/10/11 15:31
 */

@RestController
@RequestMapping("/FanFaultAlarm")
public class FanFaultAlarmController implements FanFaultAlarmControllerApi {


    @Autowired
    private TbFanFaultAlarmServiceImpl fanFaultAlarmService;


    @Override
    @LogAnnotation
    @ApiOperation(value = "添加告警")
    @PutMapping("/addFanfaultalarm")
    public ResponseResult addFanfaultalarm(@RequestBody TbFanfaultalarm fanfaultalarm) {
        if (fanfaultalarm == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return fanFaultAlarmService.addFanfaultalarm(fanfaultalarm);
    }

    @Override
    @GetMapping("/findList")
    public Result findList() {
        return fanFaultAlarmService.findList();
    }
}
