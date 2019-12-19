package com.zhansheng.management_system.AIcontroller;


import com.zhansheng.api.aiManager.AikeysnumControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.management_system.AIservice.AikeysnumServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhansheng
 */
@RestController
@RequestMapping("/ai-keysnum")
public class AikeysnumController implements AikeysnumControllerApi {

    @Autowired
    AikeysnumServiceImpl aiKeysnumService;

    @Override
    public Result findList() {
        Result list = aiKeysnumService.findList();
        return list;
    }


    @Override
    @ApiOperation(value = "根据key查找当前的编号")
    @GetMapping("/getNumByKey")
    public Result getNumByKey(String topickey) {
        Result numByKey = aiKeysnumService.getNumByKey(topickey);
        return numByKey;
    }

}
