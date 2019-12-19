package com.zhansheng.management_system.AIcontroller;

import com.zhansheng.api.aiManager.AifeatureEnginControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbAIfeatureEngin;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.AIservice.AifeatureEnginServiceImpl;
import com.zhansheng.management_system.annotation.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author zhansheng
 * 特征工程表管理（构造数据集页面）
 */
@RestController
@RequestMapping("/aifeature-engin")
public class AifeatureEnginController implements AifeatureEnginControllerApi {

    @Autowired
    private AifeatureEnginServiceImpl aifeatureEnginService;

    @Override
    @LogAnnotation
    @ApiOperation(value = "添加特征工程表")
    @PutMapping("/addData")
    public Result addData(@RequestBody TbAIfeatureEngin aiData) throws IOException
    {
        if (aiData == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return aifeatureEnginService.addData(aiData);
    }


    @Override
    @GetMapping("/findList")
    public Result findList(String featurekey) {
        return aifeatureEnginService.findList(featurekey);
    }

    @Override
    @GetMapping("/findListBytopic")
    public Result findListBytopic(String topic) {
        return aifeatureEnginService.findListBytopic(topic);
    }


    @Override
    @GetMapping("/findListFan")
    public Result findListFan(String featurekey, String selectkey) {
        return aifeatureEnginService.findListFan(featurekey, selectkey);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "删除特征工程表")
    @DeleteMapping("/deleteById/{Id}")
    public ResponseResult deleteById(@PathVariable("Id") Integer Id) {

        return aifeatureEnginService.deleteById(Id);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "修改特征工程表")
    @PostMapping("/updateData")
    public ResponseResult updateData(@RequestBody TbAIfeatureEngin aiData) {
        if (aiData == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return aifeatureEnginService.updateData(aiData);
    }

}
