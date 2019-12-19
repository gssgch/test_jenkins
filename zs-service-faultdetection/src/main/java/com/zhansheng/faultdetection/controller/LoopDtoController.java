package com.zhansheng.faultdetection.controller;

import com.zhansheng.faultdetection.service.TbLoopDtoServiceImpl;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.request.PageLoop;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @description
 * @date 2019/7/10
 */
@Api(value = "环路管理接口", description = "环路查询接口(不验证权限)")
@RestController
@RequestMapping("LoopDto")
public class LoopDtoController {

    @Autowired
    private TbLoopDtoServiceImpl loopDtoService;


    @GetMapping("/findWindmillIdByLoopList")
    public Result findWindmillIdByLoopList(PageLoop pageLoop) {
        return loopDtoService.findWindmillIdByLoopList(pageLoop);
    }



}
