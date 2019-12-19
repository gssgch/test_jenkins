package com.zhansheng.faultdetection.controller;

import com.zhansheng.faultdetection.service.TbWindmillDtoServiceImpl;
import com.zhansheng.framework.domain.Results.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @description
 * @date 2019/7/10
 */
@Api(value = "风厂管理接口", description = "风厂查询接口(不验证权限)")
@RestController
@RequestMapping("WindmillDto")
public class WindmillDtoController {


    @Autowired
    private TbWindmillDtoServiceImpl windmillDtoService;

    @ApiOperation("查询风厂信息")
    @GetMapping("/findList")
    public Result findList() {
        return windmillDtoService.findList();
    }

}
