package com.zhansheng.management_system.controller;

import com.zhansheng.api.base.BaseControllerApi;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmreceiver;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbDictKey;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultLoc;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultType;
import com.zhansheng.management_system.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/9/29 14:11
 */

@RestController
@RequestMapping("/Base")
public class BaseController implements BaseControllerApi {

    @Autowired
    private BaseServiceImpl baseService;

    @Override
    @GetMapping("/findType")
    public List<TbFaultType> findType() {
        return baseService.findType();
    }

    @Override
    @GetMapping("/findLoc")
    public List<TbFaultLoc> findLoc() {
        return baseService.findLoc();
    }

    @Override
    @GetMapping("/findAlar")
    public List<TbAlarmreceiver> findAlar() {
        return baseService.findAlar();
    }

    @Override
    @GetMapping("/findByKey/{key}")
    public List<TbDictKey> findByKey(@PathVariable("key") String key) {
        return baseService.findByKey(key);
    }

    @Override
    @GetMapping("/findAINumByKey/{key}")
    public String findAINumByKey(@PathVariable("key") String topicKey) {
        return baseService.findAINumByKey(topicKey);
    }
}
