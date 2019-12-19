package com.zhansheng.management_system.controller;

import com.zhansheng.api.Industrialcontrol.FaulttypeControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultType;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbFaulttypeServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/9/29 17:48
 */

@RestController
@RequestMapping("/FaultType")
public class FaulttypeController implements FaulttypeControllerApi {


    @Autowired
    private TbFaulttypeServiceImpl faulttypeService;

    @Override
    @PreAuthorize("hasAuthority('falut_type_management')")
    @LogAnnotation
    @ApiOperation(value = "添加故障类型")
    @PutMapping("/addFaultType")
    public ResponseResult addFaultType(@RequestBody TbFaultType faultType) {
        if (faultType == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return faulttypeService.addFaultType(faultType);
    }

    @Override
    @PreAuthorize("hasAuthority('falut_type_management')")
    @GetMapping("/findList")
    public Result findList(PageParam pageParam) {
        return faulttypeService.findList(pageParam);
    }

    @Override
    @PreAuthorize("hasAuthority('falut_type_management')")
    @LogAnnotation
    @ApiOperation(value = "删除故障类型")
    @DeleteMapping("/deleteById/{id}")
    public ResponseResult deleteById(@PathVariable("id") Integer id) {
        return faulttypeService.deleteById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('falut_type_management')")
    @LogAnnotation
    @ApiOperation(value = "修改故障类型")
    @PostMapping("/updateFaultType")
    public ResponseResult updateFaultType(@RequestBody TbFaultType faultType) {
        if (faultType == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return faulttypeService.updateFaultType(faultType);

    }
}
