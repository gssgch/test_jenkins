package com.zhansheng.management_system.controller;

import com.zhansheng.api.base.ExcelControllerApi;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.dao.TbFanpowerreportMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/10/12 11:42
 */

@RequestMapping("/Excel")
@RestController
public class ExcelController implements ExcelControllerApi {

    @Autowired
    private TbFanpowerreportMapper fanpowerreportMapper;

    @Override
    @LogAnnotation
    @ApiOperation(value = "发电量Excel导出")
    @PostMapping("/findByDay")
    public void findByDay(@RequestParam Map<String, String> map, HttpServletRequest request, HttpServletResponse response) {

        System.out.println("start导出");
        long start = System.currentTimeMillis();


        long end = System.currentTimeMillis();
        System.out.println("end导出");
        System.out.println("耗时：" + (end - start) + "s");


    }


}
