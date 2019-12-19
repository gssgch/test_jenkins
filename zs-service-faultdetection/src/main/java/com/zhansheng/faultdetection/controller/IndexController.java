package com.zhansheng.faultdetection.controller;

import com.zhansheng.api.monitor.TbIndexInterface;
import com.zhansheng.faultdetection.service.TbIndexServiceImpl;
import com.zhansheng.framework.domain.Results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @description
 * @date 2019/6/21
 */

@RestController
@RequestMapping("/Index")
public class IndexController implements TbIndexInterface {

    @Autowired
    private TbIndexServiceImpl brokenService;

//    @PreAuthorize("hasAuthority('wind_index')")
    /*@PostMapping("/IndexList/{windmillId}")
    public Result findPageList(@PathVariable("windmillId") int windmillId) {
        return brokenService.findPageList(windmillId);
    }*/

    @Override
    @PostMapping("/findPageListNew/{windmillId}")
    public Result findPageListNew(@PathVariable("windmillId") int windmillId) {
        return brokenService.findPageListNew(windmillId);
    }

    @Override
    @GetMapping("/findModel/{number}")
    public Result findModel(@PathVariable("number") int number) {
        return brokenService.findModel(number);
    }

}
