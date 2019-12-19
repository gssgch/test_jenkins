package com.zhansheng.faultdetection.controller;

import com.zhansheng.api.Industrialcontrol.FaultmanualControllerApi;
import com.zhansheng.faultdetection.service.TbFaultmanualServiceImpl;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultmanual;
import com.zhansheng.framework.domain.UserManager.request.FaultmanualDto;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/12/5 19:17
 */

@RestController
@RequestMapping("/Faultmanual")
public class FaultmanualController implements FaultmanualControllerApi {

    @Autowired
    private TbFaultmanualServiceImpl faultmanualService;


    @Override
    @PutMapping("/addFaultmanual")
    public ResponseResult addFaultmanual(@RequestBody TbFaultmanual faultmanual) {
        if (faultmanual == null) {
            return new ResponseResult(CommonCode.INVALID_PARAM);
        }
        return faultmanualService.addFaultmanual(faultmanual);
    }

    @Override
    @PostMapping("/updateFaultmanual")
    public ResponseResult updateFaultmanual(@RequestBody TbFaultmanual faultmanual) {
        if (faultmanual == null) {
            return new ResponseResult(CommonCode.INVALID_PARAM);
        }
        return faultmanualService.updateFaultmanual(faultmanual);
    }

    @Override
    @DeleteMapping("/deletById/{id}")
    public ResponseResult deleteById(@PathVariable("id") int id) {
        return faultmanualService.deleteById(id);
    }

    @Override
    @GetMapping("/findByPageList")
    public Result findByPageList(FaultmanualDto pageParam) {
        return faultmanualService.findByPageList(pageParam);
    }
}
