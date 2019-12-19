package com.zhansheng.management_system.controller;

import com.zhansheng.api.Industrialcontrol.FanFaultControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFault;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbFanFaultServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author xufu
 * @description 故障中心（传动链故障）（风机故障表处理）
 * @date 2019/9/30 10:28
 */

@RestController
@RequestMapping("/FanFaultdrivechain")
public class FanFaultdrivechainController implements FanFaultControllerApi {


    @Autowired
    private TbFanFaultServiceImpl fanFaultService;

    @Override
    @PreAuthorize("hasAuthority('drivechain_management')")
    @LogAnnotation
    @ApiOperation(value = "添加故障中心（传动链故障）")
    @PutMapping("/addFanFault")
    public ResponseResult addFanFault(@RequestBody TbFault fault) {
        if (fault == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return fanFaultService.addFanFault(fault);
    }


    @Override
    @PreAuthorize("hasAuthority('drivechain_management')")
    @GetMapping("/findList")
    public Result findList(PageParam pageParam, Integer faultFrom) {
        return fanFaultService.findList(pageParam, faultFrom);
    }

    @Override
    @PreAuthorize("hasAuthority('drivechain_management')")
    @LogAnnotation
    @ApiOperation(value = "删除故障中心（传动链故障）")
    @DeleteMapping("/deleteById/{Id}")
    public ResponseResult deleteById(@PathVariable("Id") int Id) {
        return fanFaultService.deleteById(Id);
    }

    @Override
    @PreAuthorize("hasAuthority('drivechain_management')")
    @LogAnnotation
    @ApiOperation(value = "修改故障中心（传动链故障）")
    @PostMapping("/updateFanFault")
    public ResponseResult updateFanFault(@RequestBody TbFault fault) {
        if (fault == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return fanFaultService.updateFanFault(fault);
    }
}
