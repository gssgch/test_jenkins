package com.zhansheng.management_system.controller;

import com.zhansheng.api.Industrialcontrol.AlarmreceiverControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmreceiver;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbAlarmreceiverServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/9/27 17:27
 */

@RestController
@RequestMapping("/Alarmreceiver")
public class AlarmreceiverController implements AlarmreceiverControllerApi {


    @Autowired
    private TbAlarmreceiverServiceImpl alarmreceiverService;


    @Override
    @LogAnnotation
    @PreAuthorize("hasAuthority('alarmreceiver_management')")
    @ApiOperation(value = "添加告警处理人")
    @PutMapping("/addAlarmreceiver")
    public ResponseResult addAlarmreceiver(@RequestBody TbAlarmreceiver alarmreceiver) {
        if (alarmreceiver == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return alarmreceiverService.addAlarmreceiver(alarmreceiver);
    }


    @Override
    @PreAuthorize("hasAuthority('alarmreceiver_management')")
    @GetMapping("/findList")
    public Result findList(PageParam pageParam) {
        return alarmreceiverService.findList(pageParam);
    }

    @Override
    @PreAuthorize("hasAuthority('alarmreceiver_management')")
    @LogAnnotation
    @ApiOperation(value = "删除告警处理人")
    @DeleteMapping("/deleteById/{id}")
    public ResponseResult deleteById(@PathVariable("id") int id) {
        return alarmreceiverService.deleteById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('alarmreceiver_management')")
    @LogAnnotation
    @ApiOperation(value = "修改告警处理人")
    @PostMapping("/updateAlarmreceiver")
    public ResponseResult updateAlarmreceiver(@RequestBody TbAlarmreceiver alarmreceiver) {
        if (alarmreceiver == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return alarmreceiverService.updateAlarmreceiver(alarmreceiver);
    }
}
