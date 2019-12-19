package com.zhansheng.management_system.AIcontroller;


import com.zhansheng.api.aiManager.AimodelControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbAImodel;
import com.zhansheng.framework.domain.aiManager.TbAImodelpreparam;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.AIservice.AimodelServiceImpl;
import com.zhansheng.management_system.annotation.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhansheng
 * 模型表管理
 */
@RestController
@RequestMapping("/aimodel")
public class AimodelController implements AimodelControllerApi {

    @Autowired
    private AimodelServiceImpl aimodelService;

    @Override
    @LogAnnotation
    @ApiOperation(value = "添加特征选取表")
    @PutMapping("/addData")
    public ResponseResult addData(@RequestBody TbAImodel aiData)
    {
        if (aiData == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return aimodelService.addData(aiData);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "添加模型预测参数表")
    @PutMapping("/addDatamodelpreparam")
    public Result addDatamodelpreparam(@RequestBody TbAImodelpreparam aiData)
    {
        if (aiData == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return aimodelService.addDatamodelpreparam(aiData);
    }

    @Override
    @GetMapping("/findList")
    public Result findList(String modelkey) {
        return aimodelService.findList(modelkey);
    }

    @Override
    @GetMapping("/findListByThreeKey")
    public Result findListByThreeKey(String topic, String featureselkey, String algokey) {
        return aimodelService.findListByThreeKey(topic, featureselkey, algokey);
    }

    @Override
    @GetMapping("/findColumns")
    public Result findColumns(String modelkey) {
        return aimodelService.findColumns(modelkey);
    }

    //@Override
    @GetMapping("/findListByKey")
    public Result findListByKey(String topic) {
        return aimodelService.findListByKey(topic);
    }


    @Override
    @GetMapping("/findListTestResult")
    public Result findListTestResult(String modelkey) {

        return aimodelService.findList(modelkey);
    }

    @Override
    @GetMapping("/findListTrainResult")
    public Result findListTrainResult(String modelkey) {

        return aimodelService.findList(modelkey);
    }

    @Override
    @GetMapping("/findListModelpreparam")
    public Result findListModelpreparam(String paramkey) {

        return aimodelService.findList(paramkey);
    }

    @Override
    @GetMapping("/findListModelpreresult")
    public Result findListModelpreresult(String paramkey) {

        return aimodelService.findList(paramkey);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "删除特征选取表")
    @DeleteMapping("/deleteById/{Id}")
    public ResponseResult deleteById(@PathVariable("Id") Integer Id) {

        return aimodelService.deleteById(Id);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "修改特征选取表")
    @PostMapping("/updateData")
    public ResponseResult updateData(@RequestBody TbAImodel aiData) {
        if (aiData == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return aimodelService.updateData(aiData);
    }

}
