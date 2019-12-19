package com.zhansheng.management_system.controller;

import com.zhansheng.api.cmsuser.TbLogsInterface;

import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbLog;
import com.zhansheng.framework.domain.UserManager.request.LogsParamList;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbLogsServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @description
 * @date 2019/5/16
 */

@RestController
@RequestMapping("/Logs")
public class LogsController implements TbLogsInterface {

    @Autowired
    private TbLogsServiceImpl logsService;

    @PostMapping(value = "/findPageList")
    public Result findPageList(@RequestBody LogsParamList param) {
        return logsService.findPageList(param);
    }

    @PostMapping(value = "/save")
    public Result save(@RequestBody TbLog tbLog) {
        return logsService.save(tbLog);
    }

    @GetMapping(value = "/saveLogin")
    public void saveLogin(@PathVariable("userName") String userName, @PathVariable("module") String module,
                          @PathVariable("flag") String flag, @PathVariable("remark") String remark) {
        logsService.saveLogin(userName, module, flag, remark);
    }

}
