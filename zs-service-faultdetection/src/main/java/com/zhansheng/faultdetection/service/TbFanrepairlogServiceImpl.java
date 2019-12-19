package com.zhansheng.faultdetection.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.Industrialcontrol.FanrepairlogControllerApi;
import com.zhansheng.faultdetection.dao.TbFanrepairlogMapper;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmrules;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanrepairlog;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/12/5 19:00
 */

@Service
@Transactional
public class TbFanrepairlogServiceImpl implements FanrepairlogControllerApi {

    @Autowired
    private TbFanrepairlogMapper fanrepairlogMapper;

    @Override
    public ResponseResult addFanrepairlog(TbFanrepairlog fanrepairlog) {
        TbFanrepairlog tbFanrepairlog = fanrepairlog;
        tbFanrepairlog.setCreatetime(new Date());
        tbFanrepairlog.setRepairtime(new Date());
        int i = fanrepairlogMapper.insert(tbFanrepairlog);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public ResponseResult updateFanrepairlog(TbFanrepairlog fanrepairlog) {
        TbFanrepairlog tbFanrepairlog = fanrepairlog;
        tbFanrepairlog.setRepairtime(new Date());
        int i = fanrepairlogMapper.updateByPrimaryKey(tbFanrepairlog);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public ResponseResult deleteById(int id) {
        int i = fanrepairlogMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public Result findByPageList(PageParam pageParam) {
        if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<TbFanrepairlog> list = fanrepairlogMapper.findList();
        PageInfo<TbFanrepairlog> pageInfo = new PageInfo<TbFanrepairlog>(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", pageInfo.getList());
        map.put("size", pageInfo.getTotal());
        return new Result(true, StatusCode.OK, "查询成功 !", map);

    }
}
