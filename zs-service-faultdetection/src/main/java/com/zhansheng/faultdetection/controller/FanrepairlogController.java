package com.zhansheng.faultdetection.controller;

import com.zhansheng.api.Industrialcontrol.FanrepairlogControllerApi;
import com.zhansheng.faultdetection.service.TbFanrepairlogServiceImpl;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanrepairlog;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/12/5 18:59
 */

@RestController
@RequestMapping("/Fanrepairlog")
public class FanrepairlogController implements FanrepairlogControllerApi {


    @Autowired
    private TbFanrepairlogServiceImpl fanrepairlogService;

    @Override
    @PutMapping("/addFanrepairlog")
    public ResponseResult addFanrepairlog(@RequestBody TbFanrepairlog fanrepairlog) {
        if (fanrepairlog == null) {
            return new ResponseResult(CommonCode.INVALID_PARAM);
        }
        return fanrepairlogService.addFanrepairlog(fanrepairlog);
    }

    @Override
    @PostMapping("/updateFanrepairlog")
    public ResponseResult updateFanrepairlog(@RequestBody TbFanrepairlog fanrepairlog) {
        if (fanrepairlog == null) {
            return new ResponseResult(CommonCode.INVALID_PARAM);
        }
        return fanrepairlogService.updateFanrepairlog(fanrepairlog);
    }

    @Override
    @DeleteMapping("/deleteById/{id}")
    public ResponseResult deleteById(@PathVariable("id") int id) {
        return fanrepairlogService.deleteById(id);
    }

    @Override
    @GetMapping("/findByPageList")
    public Result findByPageList(PageParam pageParam) {

        return fanrepairlogService.findByPageList(pageParam);
    }
}
