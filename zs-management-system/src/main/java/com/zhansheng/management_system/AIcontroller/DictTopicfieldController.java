package com.zhansheng.management_system.AIcontroller;

import com.zhansheng.api.aiManager.DictTopicfieldControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.management_system.AIservice.AialgorithmServiceImpl;
import com.zhansheng.management_system.AIservice.DictTopicfieldServiceImpl;
import com.zhansheng.management_system.annotation.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhansheng
 */
@RestController
@RequestMapping("/dict-topicfield")
public class DictTopicfieldController implements DictTopicfieldControllerApi {

    @Autowired
    private DictTopicfieldServiceImpl dictTopicfieldService;

    @Override
//    @LogAnnotation
        @ApiOperation(value = "根据主题查找所有字段")
        @GetMapping("/findByTopic")
        public Result findByTopic(int topic) {
        Result byTopic = dictTopicfieldService.findByTopic(topic);
        return byTopic;
    }
}