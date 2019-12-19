package com.zhansheng.faultdetection.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.Industrialcontrol.FaultmanualControllerApi;
import com.zhansheng.faultdetection.dao.TbFaultmanualMapper;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanFaultRepair;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanrepairlog;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultmanual;
import com.zhansheng.framework.domain.UserManager.request.FaultmanualDto;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/12/5 19:18
 */

@Service
@Transactional
public class TbFaultmanualServiceImpl implements FaultmanualControllerApi {

    @Autowired
    private TbFaultmanualMapper faultmanualMapper;

    @Override
    public ResponseResult addFaultmanual(TbFaultmanual faultmanual) {
        TbFaultmanual tbFaultmanual = faultmanual;
        int i = faultmanualMapper.insert(tbFaultmanual);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);

        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public ResponseResult updateFaultmanual(TbFaultmanual faultmanual) {
        TbFaultmanual tbFaultmanual = faultmanual;
        int i = faultmanualMapper.updateByPrimaryKey(tbFaultmanual);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);

        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public ResponseResult deleteById(int id) {
        int i = faultmanualMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);

        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public Result findByPageList(FaultmanualDto pageParam) {
        if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<TbFaultmanual> list = faultmanualMapper.findList(pageParam.getFaultname());
        PageInfo<TbFaultmanual> pageInfo = new PageInfo<TbFaultmanual>(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", pageInfo.getList());
        map.put("size", pageInfo.getTotal());
        return new Result(true, StatusCode.OK, "查询成功 !", map);

    }
}
