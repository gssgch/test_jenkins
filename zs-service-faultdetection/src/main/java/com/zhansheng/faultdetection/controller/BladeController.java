package com.zhansheng.faultdetection.controller;

import com.zhansheng.api.monitor.BladeInterface;
import com.zhansheng.faultdetection.dao.BaseMapper;
import com.zhansheng.faultdetection.service.BladeServiceImpl;
import com.zhansheng.framework.domain.FaultDetection.CommonList;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.request.DraListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @description
 * @date 2019/6/25
 */
@RestController
@RequestMapping("/Blade")
public class BladeController implements BladeInterface{

    @Autowired
    private BladeServiceImpl bladeService;


    /**
     * 查询叶根螺栓断裂百分比  叶片结冰状态曲线图
     *
     * @param draughtNumber
     * @return
     */
    @Override
//    @PreAuthorize("hasAuthority('wind_leaf')")
    @PostMapping("/findPageList")
    public Result findPageList(@RequestBody CommonList commonList) {
        return bladeService.findPageList(commonList);
    }

    /**
     * 查询环路对应风机的状态
     * @param commonList
     * @return
     */
    @Override
    @PostMapping("/findPageListNew")
    public Result findPageListNew(@RequestBody CommonList commonList) {
        return bladeService.findPageListNew(commonList);
    }

    @Override
    @PostMapping("/findBydraughtNumberListNew/{draughtNumber}")
    public Result findBydraughtNumberListNew(@PathVariable("draughtNumber") int draughtNumber) {
        return bladeService.findBydraughtNumberListNew(draughtNumber);
    }

    @Override
    @GetMapping("/findNumberListNew")
    public Result findNumberListNew(DraListDto draListDto) {
        return bladeService.findNumberListNew(draListDto);
    }



    /**
     * 叶片结冰详情趋势图
     * @param draughtNumber
     * @return
     */
    @Override
    @PreAuthorize("hasAuthority('wind_leaf')")
    @PostMapping("/findBydraughtNumberList/{draughtNumber}")
    public Result findBydraughtNumberList(@PathVariable("draughtNumber") int draughtNumber) {
        return bladeService.findBydraughtNumberList(draughtNumber);
    }

}
    /**
     * 叶片结冰(百分比)
     * @param draughtNumber
     * @return
     */
    /*@Override
    @PreAuthorize("hasAuthority('wind_leaf')")
    @PostMapping("/findBydraughtNumber/{draughtNumber}")
    public Result findBydraughtNumber(@PathVariable("draughtNumber") int draughtNumber) {
        return bladeService.findBydraughtNumber(draughtNumber);
    }*/