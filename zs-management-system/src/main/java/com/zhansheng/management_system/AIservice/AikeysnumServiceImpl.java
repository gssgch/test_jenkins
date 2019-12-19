package com.zhansheng.management_system.AIservice;

import com.zhansheng.api.aiManager.AikeysnumControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbAIKeysnum;
import com.zhansheng.management_system.AIdao.TbAIkeysnumMapper;
import com.zhansheng.management_system.service.BaseServiceImpl;
import com.zhansheng.management_system.utils.AIConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhansheng
 */
@Service
public class AikeysnumServiceImpl implements AikeysnumControllerApi {

    @Autowired
    private TbAIkeysnumMapper tbAIKeysnumMapper;

    @Autowired
    private BaseServiceImpl baseServiceImpl;


    @Override
    public Result findList() {
        List<TbAIKeysnum> list = tbAIKeysnumMapper.findList();
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 直接从从mysql 中获取数据
     * @param topickey
     * @return
     */
    @Override
    public Result getNumByKey(String topickey) {
        String strnum = tbAIKeysnumMapper.getNumByKey(topickey.trim()); // mysql
        String curnum = null;

        if(strnum != null) {
            curnum = String.format(AIConstant.AI_KEY_CONVERING, Integer.parseInt(strnum) + 1);
        } else {
            return new Result(true, StatusCode.OK, "查询成功 !", null);
        }


//        baseServiceImpl.findAINumByKey(topickey);// redis
        return new Result(true, StatusCode.OK, "查询成功 !", topickey.trim()+curnum);
    }
}
