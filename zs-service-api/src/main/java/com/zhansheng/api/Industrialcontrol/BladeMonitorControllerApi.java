package com.zhansheng.api.Industrialcontrol;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "叶片监测管理接口", description = "叶片监测接口提供增删改查方法")
public interface BladeMonitorControllerApi {

    @ApiOperation("分页查询叶片监测")
    public Result findList(PageParam pageParam, Integer healthyState, Integer faultFrom);

    @ApiOperation("查询远程巡检")
    public Result remoteInspection(Integer faultFrom, Integer fDraughtNumber) throws Exception;

    @ApiOperation("播放视频")
    public void playVideo(HttpServletRequest req, HttpServletResponse resp, String filePath) throws ClassNotFoundException, IOException;

    @ApiOperation("播放音频")
    public Result playVoice(HttpServletRequest req, HttpServletResponse resp, String filePath) throws ClassNotFoundException, Exception;

    @ApiOperation("播放振动")
    public Result playVibration(HttpServletRequest req, HttpServletResponse resp, String filePath) throws ClassNotFoundException, Exception;
}
