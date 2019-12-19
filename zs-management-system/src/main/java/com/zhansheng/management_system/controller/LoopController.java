package com.zhansheng.management_system.controller;

import com.zhansheng.api.cmsowner.TbLoopInterface;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Loop;
import com.zhansheng.framework.domain.UserManager.request.LoopDto;
import com.zhansheng.framework.domain.UserManager.request.PageLoop;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbLoopServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * @author
 * @description
 * @date 2019/6/14
 */


@RestController
@RequestMapping("/Loop")
public class LoopController implements TbLoopInterface {

    @Autowired
    private TbLoopServiceImpl loopService;

    @Override
    @LogAnnotation
    @ApiOperation(value = "添加环路")
    @PreAuthorize("hasAuthority('loop_management')")
    @PostMapping("/addLoop")
    public Result addLoop(@RequestBody LoopDto loopDto) {
        return loopService.addLoop(loopDto);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "删除环路")
    @PreAuthorize("hasAuthority('loop_management')")
    @DeleteMapping("/deleteById/{loopId}")
    public Result deleteById(@PathVariable("loopId") int loopId) {
        return loopService.deleteById(loopId);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "修改环路")
    @PreAuthorize("hasAuthority('loop_management')")
    @PostMapping("/updateLoop")
    public Result updateLoop(@RequestBody Loop loop) {
        return loopService.updateLoop(loop);
    }

    @Override
    @PreAuthorize("hasAuthority('loop_management')")
    @PostMapping("/findWindmillIdByLoopList")
    public Result findWindmillIdByLoopList(@RequestBody PageLoop pageLoop) {
        return loopService.findWindmillIdByLoopList(pageLoop);
    }



}
