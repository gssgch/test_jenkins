package com.zhansheng.management_system.AIcontroller;


import com.zhansheng.api.aiManager.AifeatureSelectControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbAIfeatureSelect;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.AIservice.AifeatureSelectServiceImpl;
import com.zhansheng.management_system.annotation.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhansheng
 * 特征选取表管理（模型训练页面）
 */
@RestController
@RequestMapping("/aifeature-select")
public class AifeatureSelectController implements AifeatureSelectControllerApi {

    @Autowired
    private AifeatureSelectServiceImpl aifeatureSelectService;

    @Override
    @LogAnnotation
    @ApiOperation(value = "添加特征选取表")
    @PutMapping("/addData")
    public Result addData(@RequestBody TbAIfeatureSelect aiData)
    {
        if (aiData == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return aifeatureSelectService.addData(aiData);
    }


    @Override
    @GetMapping("/findList")
    public Result findList(String featureselkey) {
        return aifeatureSelectService.findList(featureselkey);
    }

    @Override
    @GetMapping("/findListBytopic")
    public Result findListBytopic(String topic) {
        return aifeatureSelectService.findListBytopic(topic);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "删除特征选取表")
    @DeleteMapping("/deleteById/{Id}")
    public ResponseResult deleteById(@PathVariable("Id") Integer Id) {

        return aifeatureSelectService.deleteById(Id);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "修改特征选取表")
    @PostMapping("/updateData")
    public ResponseResult updateData(@RequestBody TbAIfeatureSelect aiData) {
        if (aiData == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return aifeatureSelectService.updateData(aiData);
    }

}
