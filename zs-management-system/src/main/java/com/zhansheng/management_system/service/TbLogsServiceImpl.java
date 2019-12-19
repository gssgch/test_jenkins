package com.zhansheng.management_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.zhansheng.api.cmsuser.TbLogsInterface;

import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbLog;
import com.zhansheng.framework.domain.UserManager.request.LogsParamList;
import com.zhansheng.management_system.dao.TbLogsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author xufu
 * @description 日志查询
 * @date 2019/05/16
 */

@Service
@Transactional
public class TbLogsServiceImpl implements TbLogsInterface {

    @Autowired
    private TbLogsMapper logMapper;

    public Result save(TbLog sysLogs) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        sysLogs.setFUserName(name);

        logMapper.save(sysLogs);
        return new Result(true, StatusCode.OK, "添加成功!");
    }

    public void saveLogin(String userName, String module, String flag, String remark) {
        TbLog sysLogs = new TbLog();
        sysLogs.setFUserName(userName);
        sysLogs.setFFlag(flag);
        sysLogs.setFModule(module);
        sysLogs.setFRemark(remark);

        logMapper.save(sysLogs);
    }

    /**
     * @param param 参数集
     * @return 查询结果集
     */
    @Override
    public Result findPageList(LogsParamList param) {

        Map<String,Object> map = Maps.newHashMap();

        //分页参数
        if(param.getPage() <=0){
            param.setPage(1);
        }
        if(param.getSize()<=0){
            param.setSize(10);
        }
        PageHelper.startPage(param.getPage(), param.getSize());

        //获取日志List
        List<TbLog> list = logMapper.findPageList(param.getUserName(), param.getModule(),param.getFlag(),
                param.getStartTime(),param.getEndTime());

        PageInfo<TbLog> pageInfo = new PageInfo<TbLog>(list);

        map.put("row",pageInfo.getList());
        map.put("rowCount",pageInfo.getTotal());
        return new Result(true,StatusCode.OK,"查询成功!",map);
    }

}
