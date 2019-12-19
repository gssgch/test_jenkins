package com.zhansheng.faultdetection.controller;

import com.zhansheng.api.monitor.TbMonitorInterface;
import com.zhansheng.faultdetection.service.TbMonitorServiceImpl;
import com.zhansheng.framework.domain.FaultDetection.CommonList;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.request.DraListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * @author
 * @description
 * @date 2019/5/16
 */

@RestController
@RequestMapping("/Monitor")
public class MonitorController implements TbMonitorInterface {

    @Autowired
    private TbMonitorServiceImpl monitorService;

    @Override
    @PreAuthorize("hasAuthority('wind_blade')")
    @PostMapping("/findPageList")
    public Result findPageList(@RequestBody CommonList commonList) {
        return monitorService.findPageList(commonList);
    }

    @Override
    @PostMapping("/findPageListNew")
    public Result findPageListNew(@RequestBody CommonList commonList) {
        return monitorService.findPageListNew(commonList);
    }

    @Override
    @GetMapping("/findNumberListNew")
    public Result findNumberListNew(DraListDto draListDto) {
        return monitorService.findNumberListNew(draListDto);
    }


    @Override
//    @PreAuthorize("hasAuthority('wind_blade')")
    @PostMapping("/findBydraughtNumberList/{draughtNumber}")
    public Result findBydraughtNumberList(@PathVariable("draughtNumber") int draughtNumber) {
        return monitorService.findBydraughtNumberList(draughtNumber);
    }

    @Override
    @PostMapping("/findBydraughtNumberListNew/{draughtNumber}")
    public Result findBydraughtNumberListNew(@PathVariable("draughtNumber") int draughtNumber) {
        return monitorService.findBydraughtNumberListNew(draughtNumber);
    }

}
    /**
     * 叶根螺栓状态(百分比)
     * @param draughtNumber
     * @return
     */
    /*@Override
    @PreAuthorize("hasAuthority('wind_blade')")
    @PostMapping("/findBydraughtNumber/{draughtNumber}")
    public Result findBydraughtNumber(@PathVariable("draughtNumber") int draughtNumber) {
        return monitorService.findBydraughtNumber(draughtNumber);
    }*/