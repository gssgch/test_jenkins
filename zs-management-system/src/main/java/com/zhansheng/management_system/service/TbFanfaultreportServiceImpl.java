package com.zhansheng.management_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.Industrialcontrol.FanfaultreportControlerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanfaultreport;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.request.PowerrePort;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.management_system.dao.TbFanfaultreportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/10/21 10:07
 */


@Transactional
@Service
public class TbFanfaultreportServiceImpl implements FanfaultreportControlerApi {

    @Autowired
    private TbFanfaultreportMapper fanfaultreportMapper;

    public Result findByYMD(PowerrePort powerrePort, PageParam pageParam) {

        if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<TbFanfaultreport> list = getFanfaultreportList(powerrePort);
        PageInfo<TbFanfaultreport> pageInfo = new PageInfo<TbFanfaultreport>(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", pageInfo.getList());
        map.put("size", pageInfo.getTotal());
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    public List<TbFanfaultreport> getFanfaultreportList(PowerrePort powerrePort) {
        List<TbFanfaultreport> list = null;
        list = fanfaultreportMapper.fanFaultSumNum(powerrePort);
        return list;
    }

    public Result findByloc(PowerrePort powerrePort, PageParam pageParam) {
        if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<TbFanfaultreport> list = getFanfaultreportListLoc(powerrePort);
        PageInfo<TbFanfaultreport> pageInfo = new PageInfo<TbFanfaultreport>(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", pageInfo.getList());
        map.put("size", pageInfo.getTotal());
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    public List<TbFanfaultreport> getFanfaultreportListLoc(PowerrePort powerrePort) {
        List<TbFanfaultreport> list = fanfaultreportMapper.fanFaultLoc(powerrePort);
        return list;
    }

    public Result findBylevel(PowerrePort powerrePort, PageParam pageParam) {

        if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<TbFanfaultreport> list = getFanfaultreportListLevel(powerrePort);
        PageInfo<TbFanfaultreport> pageInfo = new PageInfo<TbFanfaultreport>(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", pageInfo.getList());
        map.put("size", pageInfo.getTotal());
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    public List<TbFanfaultreport> getFanfaultreportListLevel(PowerrePort powerrePort) {

        List<TbFanfaultreport> list = fanfaultreportMapper.fanFaultLevel(powerrePort);

        return list;

    }

    //导出excel


}
