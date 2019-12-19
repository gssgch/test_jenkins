package com.zhansheng.management_system.controller;

import com.zhansheng.api.model.IndustrialIndexControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFault;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.request.CommonListI;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.management_system.service.IndustrialIndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/10/15 16:19
 */
@RestController
@RequestMapping("/IndustrialIndex")
public class IndustrialIndexController implements IndustrialIndexControllerApi {


    @Autowired
    private IndustrialIndexServiceImpl indexService;


    @Override
    @GetMapping("/findFaultCount")
    public int findFaultCount() {
        return indexService.findFaultCount();
    }

    @Override
    @GetMapping("/findFault")
    public List<TbFault> findFault() {
        return indexService.findFault();
    }

    @Override
    @GetMapping("/findByState")
    public Result findByState(CommonListI commonList) {
        if (commonList == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return indexService.findByState(commonList);
    }

}