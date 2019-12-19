package com.zhansheng.faultdetection.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.faultdetection.dao.TbLoopDtoMapper;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Loop;
import com.zhansheng.framework.domain.UserManager.request.PageLoop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @description
 * @date 2019/7/10
 */
@Service
public class TbLoopDtoServiceImpl {


    @Autowired
    private TbLoopDtoMapper loopDtoMapper;


    public Result findWindmillIdByLoopList(PageLoop pageLoop) {

        if (pageLoop.getPage() <= 0) {
            pageLoop.setPage(1);
        }
        if (pageLoop.getSize() <= 0) {
            pageLoop.setSize(10);
        }
        //如果username是空就是没有模糊匹配  查询所有结果分页
        if (pageLoop != null) {
            PageHelper.startPage(pageLoop.getPage(), pageLoop.getSize());
            List<Loop> list = loopDtoMapper.findByloopIdAndDraughtfanList(pageLoop.getWindmillId());
            PageInfo<Loop> pageInfo = new PageInfo<Loop>(list);
            Map<Object, Object> map = new HashMap<>();
            map.put("page", pageInfo.getList());
            map.put("size", pageInfo.getTotal());
            return new Result(true, StatusCode.OK, "查询所有风机信息!", map);
        }
        return new Result(false, StatusCode.REPERROR, "查询失败!");
    }





}
