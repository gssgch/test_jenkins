package com.zhansheng.management_system.AIcontroller;

import com.zhansheng.api.aiManager.AialgorithmControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.domain.aiManager.TbAIalgorithm;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.AIservice.AialgorithmServiceImpl;
import com.zhansheng.management_system.annotation.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author zhansheng
 * 算法表管理（算法中心页面）
 */
@RestController
@RequestMapping("/aialgorithm")
public class AialgorithmController implements AialgorithmControllerApi {
    @Autowired
    private AialgorithmServiceImpl aialgorithmService;

    @Override
    @LogAnnotation
    @ApiOperation(value = "添加算法表")
    @PutMapping("/addData")
    public ResponseResult addData(@RequestBody TbAIalgorithm aiData)throws IOException
    {
        if (aiData == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return aialgorithmService.addData(aiData);
    }


    @Override
    @GetMapping("/findList")
    public Result findList(PageParam pageParam, String algokey) {
        return aialgorithmService.findList(pageParam, algokey);
    }

    @Override
    @GetMapping("/findListBykey")
    public Result findListBykey(String topic) {
        return aialgorithmService.findListBykey(topic);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "删除算法表")
    @DeleteMapping("/deleteById/{Id}")
    public ResponseResult deleteById(@PathVariable("Id") Integer Id) {

        return aialgorithmService.deleteById(Id);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "修改算法表")
    @PostMapping("/updateData")
    public ResponseResult updateData(@RequestBody TbAIalgorithm aiData) {
        if (aiData == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return aialgorithmService.updateData(aiData);
    }
}
