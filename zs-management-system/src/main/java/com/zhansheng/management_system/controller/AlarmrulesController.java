package com.zhansheng.management_system.controller;

import com.zhansheng.api.Industrialcontrol.AlarmrulesControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmrules;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbAlarmrulesServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/9/29 13:51
 */
@RestController
@RequestMapping("/Alarmrules")
public class AlarmrulesController implements AlarmrulesControllerApi {

    @Autowired
    private TbAlarmrulesServiceImpl alarmrulesService;

    @Override
    @PreAuthorize("hasAuthority('alarmrules_management')")
    @LogAnnotation
    @ApiOperation(value = "添加告警策略")
    @PutMapping("/addAlarmrules")
    public ResponseResult addAlarmrules(@RequestBody TbAlarmrules alarmrules) {
        if (alarmrules == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return alarmrulesService.addAlarmrules(alarmrules);
    }

    @Override
    @PreAuthorize("hasAuthority('alarmrules_management')")
    @GetMapping("/findList")
    public Result findList(PageParam pageParam) {
        return alarmrulesService.findList(pageParam);
    }

    @Override
    @PreAuthorize("hasAuthority('alarmrules_management')")
    @LogAnnotation
    @ApiOperation(value = "删除告警策略")
    @DeleteMapping("/deleteById/{id}")
    public ResponseResult deleteById(@PathVariable("id") int id) {
        return alarmrulesService.deleteById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('alarmrules_management')")
    @LogAnnotation
    @ApiOperation(value = "修改告警策略")
    @PostMapping("/updateAlarmrules")
    public ResponseResult updateAlarmrules(@RequestBody TbAlarmrules alarmrules) {
        if (alarmrules == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return alarmrulesService.updateAlarmrules(alarmrules);
    }
}
