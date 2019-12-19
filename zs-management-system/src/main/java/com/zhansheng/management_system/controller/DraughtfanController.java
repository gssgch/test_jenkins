package com.zhansheng.management_system.controller;

import com.zhansheng.api.cmsowner.TbDraughtfanInterface;


import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbDraughtfan;
import com.zhansheng.framework.domain.UserManager.request.DraughtfanDto;
import com.zhansheng.framework.domain.UserManager.request.PageDraughtfan;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbDraughtfanServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @description
 * @date 2019/6/18
 */

@RestController
@RequestMapping("/Draughtfan")
public class DraughtfanController implements TbDraughtfanInterface {

    @Autowired
    private TbDraughtfanServiceImpl draughtfanService;

    @Override
    @LogAnnotation
    @ApiOperation(value = "添加风机")
    @PreAuthorize("hasAuthority('draughtfan_management')")
    @PostMapping("/addDraughtfan")
    public Result addDraughtfan(@RequestBody DraughtfanDto draughtfanDto) {
        return draughtfanService.addDraughtfan(draughtfanDto);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "删除风机")
    @PreAuthorize("hasAuthority('draughtfan_management')")
    @DeleteMapping("/deleteById/{draughtfanId}")
    public ResponseResult deleteById(@PathVariable("draughtfanId") int draughtfanId) {
        return draughtfanService.deleteById(draughtfanId);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "修改风机")
    @PreAuthorize("hasAuthority('draughtfan_management')")
    @PostMapping("/updateDraughtfan")
    public Result updateDraughtfan(@RequestBody TbDraughtfan draughtfan) {
        return draughtfanService.updateDraughtfan(draughtfan);
    }

    @Override
    @PreAuthorize("hasAuthority('draughtfan_management')")
    @PostMapping("/findDraughtfanList")
    public Result findDraughtfanList(@RequestBody PageDraughtfan pageDraughtfan) {
        return draughtfanService.findDraughtfanList(pageDraughtfan);
    }

    @Override
    @PreAuthorize("hasAuthority('draughtfan_management')")
    @PostMapping("/findDraughtfanNO")
    public Result findDraughtfanNO() {
        return draughtfanService.findDraughtfanNO();
    }


}
