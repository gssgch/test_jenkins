package com.zhansheng.management_system.controller;

import com.zhansheng.api.cmsowner.TbWindmillInterface;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbWindmill;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;

import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbWindmillServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 * @description
 * @date 2019/6/14
 */
@RestController
@RequestMapping("/Windmill")
public class WindmillController implements TbWindmillInterface {

    @Autowired
    private TbWindmillServiceImpl windmillService;

    @Override
    @LogAnnotation
    @ApiOperation(value = "添加风厂")
    @PreAuthorize("hasAuthority('windmill_management')")
    @PostMapping("/addOwnerAndWindmill")
    public Result addWindmill(@RequestBody TbWindmill windmill) {
        return windmillService.addWindmill(windmill);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "删除风厂")
    @PreAuthorize("hasAuthority('windmill_management')")
    @DeleteMapping("/deleteById/{windmillId}")
    public Result deleteById(@PathVariable("windmillId") int windmillId) {
        return windmillService.deleteById(windmillId);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "修改风厂")
    @PreAuthorize("hasAuthority('windmill_management')")
    @PostMapping("/updateByWindmill")
    public Result updateByWindmill(@RequestBody TbWindmill windmill) {
        return windmillService.updateByWindmill(windmill);
    }

    @Override
    @PreAuthorize("hasAuthority('windmill_management')")
    @PostMapping("/findList")
    public Result findList() {
        return windmillService.findList();
    }

    @PreAuthorize("hasAuthority('windmill_management')")
    @ApiOperation(value = "Excel批量添加")
    @PostMapping("/importExcel")
    public ResponseResult importExcel(MultipartFile file,
                                      String usename,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        if (file == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return windmillService.readExcelFile(file,usename);
    }


}
