package com.zhansheng.faultdetection.controller;

import com.zhansheng.api.monitor.TbDegradationInterface;
import com.zhansheng.faultdetection.service.TbDegradationServiceImpl;
import com.zhansheng.framework.domain.FaultDetection.CommonList;
import com.zhansheng.framework.domain.Results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @description
 * @date 2019/6/26
 */
@RestController
@RequestMapping("/Degradation")
public class DegradationController implements TbDegradationInterface {

    /*@Autowired
    private TbDegradationServiceImpl degradationService;

    @Override
    @PostMapping("/findPageList")
    public Result findPageList(@RequestBody CommonList commonList) {
        return degradationService.findPageList(commonList);
    }

    @Override
    @PostMapping("/findBydraughtNumber/{draughtNumber}")
    public Result findBydraughtNumber(@PathVariable("draughtNumber") int draughtNumber) {
        return degradationService.findBydraughtNumber(draughtNumber);
    }

    @Override
    @PostMapping("/findBydraughtNumberList/{draughtNumber}")
    public Result findBydraughtNumberList(@PathVariable("draughtNumber") int draughtNumber) {
        return degradationService.findBydraughtNumberList(draughtNumber);
    }*/

}
