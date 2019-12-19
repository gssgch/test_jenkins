package com.zhansheng.management_system.AIcontroller;

import com.zhansheng.api.aiManager.AirawfieldselectControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.domain.aiManager.TbAIrawfieldselect;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.AIservice.AirawfieldselectServiceImpl;
import com.zhansheng.management_system.annotation.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhansheng
 * 原始字段选取表管理（特征提取页面）
 */
@RestController
@RequestMapping("/airawfieldselect")
public class AirawfieldselectController implements AirawfieldselectControllerApi {
    @Autowired
    private AirawfieldselectServiceImpl airawfieldselectService;

    @Override
    @LogAnnotation
    @ApiOperation(value = "添加原始字段选取表")
    @PutMapping("/addData")
    public Result addData(@RequestBody TbAIrawfieldselect aiData)
  {
        if (aiData == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return airawfieldselectService.addData(aiData);
    }


    @Override
    @GetMapping("/findList")
    public Result findList(String selectkey) {
        return airawfieldselectService.findList(selectkey);
    }

    @Override
    @GetMapping("/findListFan")
    public Result findListFan(String selectkey) {
        return airawfieldselectService.findListFan(selectkey);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "删除原始字段选取表")
    @DeleteMapping("/deleteById/{Id}")
    public ResponseResult deleteById(@PathVariable("Id") Integer Id) {
        return airawfieldselectService.deleteById(Id);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "修改原始字段选取表")
    @PostMapping("/updateData")
    public ResponseResult updateData(@RequestBody TbAIrawfieldselect aiData) {
        if (aiData == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return airawfieldselectService.updateData(aiData);
    }
}
