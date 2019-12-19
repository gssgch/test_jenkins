package com.zhansheng.management_system.controller;

import com.zhansheng.api.Industrialcontrol.FaultlocControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultLoc;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbFaultlocServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/9/29 17:51
 */
@RestController
@RequestMapping("/Faultloc")
public class FaultlocController implements FaultlocControllerApi {


    @Autowired
    private TbFaultlocServiceImpl faultlocService;


    @Override
    @PreAuthorize("hasAuthority('fault_loc_management')")
    @LogAnnotation
    @ApiOperation(value = "删除故障位置")
    @DeleteMapping("/deleteById/{id}")
    public ResponseResult deleteById(@PathVariable("id") int id) {
        return faultlocService.deleteById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('fault_loc_management')")
    @LogAnnotation
    @ApiOperation(value = "修改故障位置")
    @PostMapping("/updateFaultLoc")
    public ResponseResult updateFaultLoc(HttpServletRequest request,
                                         HttpServletResponse response,
                                         TbFaultLoc faultLoc,
                                         MultipartFile file) {
        if (faultLoc == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return faultlocService.updateFaultLoc(request, response, faultLoc, file);
    }


    @Override
    @PreAuthorize("hasAuthority('fault_loc_management')")
    @LogAnnotation
    @ApiOperation(value = "添加故障位置")
    @PostMapping("/addFaultLoc")
    public ResponseResult addFaultLoc(HttpServletRequest request,
                                      HttpServletResponse response,
                                      TbFaultLoc faultLoc,
                                      MultipartFile file) throws IOException {

        if (faultLoc == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return faultlocService.addFaultLoc(request, response, faultLoc, file);
    }


    @Override
    @PreAuthorize("hasAuthority('fault_loc_management')")
    @GetMapping("/findList")
    public Result findList(PageParam pageParam) {
        if (pageParam == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return faultlocService.findList(pageParam);
    }


}

