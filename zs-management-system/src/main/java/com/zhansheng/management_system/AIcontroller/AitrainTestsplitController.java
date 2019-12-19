package com.zhansheng.management_system.AIcontroller;


import com.zhansheng.api.aiManager.AitrainTestsplitControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbAIfeatureSelect;
import com.zhansheng.framework.domain.aiManager.TbAItrainTestsplit;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.AIservice.AifeatureSelectServiceImpl;
import com.zhansheng.management_system.AIservice.AitrainTestsplitServiceImpl;
import com.zhansheng.management_system.annotation.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhansheng
 */
@RestController
@RequestMapping("/aitrain-testsplit")
public class AitrainTestsplitController implements AitrainTestsplitControllerApi {

    @Autowired
    private AitrainTestsplitServiceImpl aitrainTestsplitService;

    @Override
    @LogAnnotation
    @ApiOperation(value = "添加训练集测试集切分数据表")
    @PutMapping("/addData")
    public Result addData(@RequestBody TbAItrainTestsplit aiData)
    {
        if (aiData == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return aitrainTestsplitService.addData(aiData);
    }


    @Override
    @GetMapping("/findList")
    public Result findList(String featureselkey) {
        return aitrainTestsplitService.findList(featureselkey);
    }


    @Override
    @LogAnnotation
    @ApiOperation(value = "删除训练集测试集切分数据表")
    @DeleteMapping("/deleteById/{Id}")
    public ResponseResult deleteById(@PathVariable("Id") Integer Id) {

        return aitrainTestsplitService.deleteById(Id);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "修改训练集测试集切分数据表")
    @PostMapping("/updateData")
    public ResponseResult updateData(@RequestBody TbAItrainTestsplit aiData) {
        if (aiData == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return aitrainTestsplitService.updateData(aiData);
    }

}
