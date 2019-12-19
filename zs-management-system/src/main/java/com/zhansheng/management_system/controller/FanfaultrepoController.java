package com.zhansheng.management_system.controller;

import com.zhansheng.api.Industrialcontrol.FanfaultrepoControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanfaultrepo;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbFanfaultrepoServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/9/27 10:28
 */

@RestController
@RequestMapping("/Fanfaultrepo")
public class FanfaultrepoController implements FanfaultrepoControllerApi {
    @Autowired
    private TbFanfaultrepoServiceImpl fanfaultrepoService;


    @Override
    @PreAuthorize("hasAuthority('fanfaultrepo_management')")
    @LogAnnotation
    @ApiOperation(value = "添加故障知识库")
    @PutMapping("/addFanfaultrepo")
    public ResponseResult addFanfaultrepo(@RequestBody TbFanfaultrepo fanfaultrepo) {
        if (fanfaultrepo == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return fanfaultrepoService.addFanfaultrepo(fanfaultrepo);
    }
    @Override
    @PreAuthorize("hasAuthority('fanfaultrepo_management')")
    @GetMapping("/findList")
    public Result findList(PageParam pageParam) {
        return fanfaultrepoService.findList(pageParam);
    }

    @Override
    @PreAuthorize("hasAuthority('fanfaultrepo_management')")
    @LogAnnotation
    @ApiOperation(value = "删除故障知识库")
    @DeleteMapping("/deleteById/{Id}")
    public ResponseResult deleteById(@PathVariable("Id") int Id) {
        return fanfaultrepoService.deleteById(Id);
    }

    @Override
    @PreAuthorize("hasAuthority('fanfaultrepo_management')")
    @LogAnnotation
    @ApiOperation(value = "修改故障知识库")
    @PostMapping("/updateFanfaultrepo")
    public ResponseResult updateFanfaultrepo(@RequestBody TbFanfaultrepo fanfaultrepo) {
        if (fanfaultrepo == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return fanfaultrepoService.updateFanfaultrepo(fanfaultrepo);
    }
}
