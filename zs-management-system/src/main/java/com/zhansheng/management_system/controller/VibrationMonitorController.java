package com.zhansheng.management_system.controller;

import com.zhansheng.api.Industrialcontrol.BladeMonitorControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.management_system.service.BladeMonitorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xufu
 * @description 振动监测
 * @date 2019/9/30 10:28
 */

@RestController
@RequestMapping("/VibrationMonitor")
public class VibrationMonitorController implements BladeMonitorControllerApi {

    @Autowired
    private BladeMonitorServiceImpl bladeMonitorService;

    @Override
    @GetMapping("/findList")
    @PreAuthorize("hasAuthority('active_surveillance')")
    public Result findList(PageParam pageParam, Integer healthyState, Integer faultFrom) {
        return bladeMonitorService.findList(pageParam, healthyState, faultFrom);
    }

    @Override
    @GetMapping("/remoteInspection")
    @PreAuthorize("hasAuthority('active_surveillance')")
    public Result remoteInspection(Integer faultFrom, Integer fDraughtNumber) throws Exception {
        return bladeMonitorService.remoteInspection(faultFrom, fDraughtNumber);
    }

    @Override
    @GetMapping("/playVideo")
    @PreAuthorize("hasAuthority('active_surveillance')")
    public void playVideo(HttpServletRequest req, HttpServletResponse resp, String filePath)
            throws ClassNotFoundException, IOException {
        bladeMonitorService.playVideo(req, resp, filePath);
    }

    @Override
    @GetMapping("/playVoice")
    @PreAuthorize("hasAuthority('active_surveillance')")
    public Result playVoice(HttpServletRequest req, HttpServletResponse resp, String filePath)
            throws ClassNotFoundException, Exception {
        return bladeMonitorService.playVoice(req, resp, filePath);
    }

    @Override
    @GetMapping("/playVibration")
    @PreAuthorize("hasAuthority('active_surveillance')")
    public Result playVibration(HttpServletRequest req, HttpServletResponse resp, String filePath)
            throws ClassNotFoundException, Exception {
        return  bladeMonitorService.playVibration(req, resp, filePath);
    }

}
