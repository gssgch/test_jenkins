package com.zhansheng.management_system.controller;

import com.zhansheng.api.Industrialcontrol.BladeMonitorControllerApi;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.management_system.service.BladeMonitorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xufu
 * @description 叶片监测
 * @date 2019/9/30 10:28
 */

@RestController
@RequestMapping("/BladeMonitor")
public class BladeMonitorController implements BladeMonitorControllerApi {

    @Autowired
    private BladeMonitorServiceImpl bladeMonitorService;

    @Override
    @GetMapping("/findList")
    @PreAuthorize("hasAuthority('bladeMonitor')")
    public Result findList(PageParam pageParam, Integer healthyState, Integer faultFrom) {
        return bladeMonitorService.findList(pageParam, healthyState, faultFrom);
    }

    @Override
    @GetMapping("/remoteInspection")
    @PreAuthorize("hasAuthority('bladeMonitor')")
    public Result remoteInspection(Integer faultFrom, Integer fDraughtNumber) throws Exception {
        return bladeMonitorService.remoteInspection(faultFrom, fDraughtNumber);
    }

    @Override
    @GetMapping("/playVideo")
    @PreAuthorize("hasAuthority('bladeMonitor')")
    public void playVideo(HttpServletRequest req, HttpServletResponse resp, String filePath)
            throws ClassNotFoundException, IOException {
        bladeMonitorService.playVideo(req, resp, filePath);
    }

    @Override
    @GetMapping("/playVoice")
    @PreAuthorize("hasAuthority('bladeMonitor')")
    public Result playVoice(HttpServletRequest req, HttpServletResponse resp, String filePath)
            throws ClassNotFoundException, Exception {
        return bladeMonitorService.playVoice(req, resp, filePath);
    }

    @Override
    @GetMapping("/playVibration")
    @PreAuthorize("hasAuthority('bladeMonitor')")
    public Result playVibration(HttpServletRequest req, HttpServletResponse resp, String filePath)
            throws ClassNotFoundException, Exception {
        return  bladeMonitorService.playVibration(req, resp, filePath);
    }

}
