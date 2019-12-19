package com.zhansheng.management_system.controller;

import com.zhansheng.api.Industrialcontrol.FanFaultRepairControllerApi;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanFaultRepair;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbFanFaultRepairServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xufu
 * @description 添加检修（故障检修表处理）
 * @date 2019/9/30 10:28
 */

@RestController
@RequestMapping("/FanFaultRepair")
public class FanFaultRepairController implements FanFaultRepairControllerApi {


    @Autowired
    private TbFanFaultRepairServiceImpl fanFaultRepairService;

    @Override
    @LogAnnotation
    @ApiOperation(value = "添加故障检修")
    @PutMapping("/addFanFaultRepair")
    public ResponseResult addFanFaultRepair(@RequestBody TbFanFaultRepair fanFaultRepair) {
        if (fanFaultRepair == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return fanFaultRepairService.addFanFaultRepair(fanFaultRepair);
    }

}
