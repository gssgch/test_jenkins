package com.zhansheng.management_system.AIservice;

import com.zhansheng.api.aiManager.DictTopicfieldControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbDictTopicfield;
import com.zhansheng.management_system.AIdao.DictTopicfieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhansheng
 */
@Service
public class DictTopicfieldServiceImpl implements DictTopicfieldControllerApi {


    @Autowired
    private DictTopicfieldMapper dictTopicfieldMapper;

    @Override
    public Result findByTopic(int topic) {
        String par = topic + "";
        System.out.println(par);
        List<TbDictTopicfield> topicFields = dictTopicfieldMapper.findByTopic(par);
        Map<Object, Object> map = new HashMap<>();
        map.put("list", topicFields);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }
}
