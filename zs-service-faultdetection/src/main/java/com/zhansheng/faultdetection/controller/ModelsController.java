package com.zhansheng.faultdetection.controller;

import com.zhansheng.api.monitor.TbModelsInterface;
import com.zhansheng.faultdetection.service.BaseService;
import com.zhansheng.faultdetection.service.TbModelsServiceImpl;
import com.zhansheng.framework.domain.FaultDetection.ModelsParamList;
import com.zhansheng.framework.domain.FaultDetection.TbState;
import com.zhansheng.framework.domain.Results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @author
 * @description
 * @date 2019/8/15
 */
@RestController
@RequestMapping("/Models")
public class ModelsController implements TbModelsInterface {

    @Autowired
    private TbModelsServiceImpl modelsService;


    /**
     * 风机正常模型和故障模型  调用此接口 (一期工程)
     * @param modelsParamList
     * @return
     */
    /*@Override
    @PostMapping("/findTimeList")
    public Result findTimeList(@RequestBody ModelsParamList modelsParamList) {
        return modelsService.findTimeList(modelsParamList);
    }


    @Autowired
    private BaseService baseService;

    *//**
     * 查询风机id  风机AI  界面调用此接口 (一期工程)
     * @return
     *//*
    @GetMapping("/findNumber")
    public int findNumber() {

        List<TbState> states = baseService.findfnormalState4();
        Random rand = new Random();
        TbState randomElement = states.get(rand.nextInt(states.size()));
        Integer draughtNumber = randomElement.getFDraughtNumber();
        return draughtNumber;
    }*/


}