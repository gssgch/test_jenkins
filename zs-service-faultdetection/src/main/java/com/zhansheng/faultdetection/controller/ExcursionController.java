package com.zhansheng.faultdetection.controller;

import com.zhansheng.api.monitor.TbExcursionInterface;
import com.zhansheng.faultdetection.service.TbExcursionServiceImpl;
import com.zhansheng.framework.domain.FaultDetection.CommonList;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.request.DraListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @description
 * @date 2019/6/26
 */

@RestController
@RequestMapping("/Excursion")
public class ExcursionController implements TbExcursionInterface {

    @Autowired
    private TbExcursionServiceImpl excursionService;

    @Override
    @PreAuthorize("hasAuthority('wind_yaw')")
    @PostMapping("/findPageList")
    public Result findPageList(@RequestBody CommonList commonList) {
        return excursionService.findPageList(commonList);
    }

    /**
     * 查询当前模型状态
     * @param commonList
     * @return
     */
    @Override
    @PostMapping("/findPageListNew")
    public Result findPageListNew(@RequestBody CommonList commonList) {
        return excursionService.findPageListNew(commonList);
    }



    @Override
    @GetMapping("/findNumberListNew")
    public Result findNumberListNew(DraListDto draListDto) {
        return excursionService.findNumberListNew(draListDto);
    }



}
