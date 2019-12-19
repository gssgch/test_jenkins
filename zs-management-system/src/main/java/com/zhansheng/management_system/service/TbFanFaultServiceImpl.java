package com.zhansheng.management_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.Industrialcontrol.AlarmrulesControllerApi;
import com.zhansheng.api.Industrialcontrol.FanFaultControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmrules;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFault;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.dao.TbAlarmrulesMapper;
import com.zhansheng.management_system.dao.TbFanFaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xufu
 * @description 告警中心（风机故障表处理）
 * @date 2019/9/30 13:44
 */
@Service
@Transactional
public class TbFanFaultServiceImpl implements FanFaultControllerApi {

    @Autowired
    private TbFanFaultMapper fanFaultMapper;

    /**
     * 向告警中心添加数据
     *
     * @param fault :
     * @return com.zhansheng.framework.model.response.ResponseResult
     * @Author xufu
     * @Date 2019/9/30 14:26
     */
    @Override
    public ResponseResult addFanFault(TbFault fault) {
        TbFault tbFanFault = fault;
        int i = fanFaultMapper.insert(tbFanFault);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 分页查询
     *
     * @param pageParam :
     * @return com.zhansheng.framework.domain.Results.Result
     * @Author xufu
     * @Date 2019/9/30 14:33
     */
    @Override
    public Result findList(PageParam pageParam, Integer faultFrom) {
        if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<TbFault> list = fanFaultMapper.findList(faultFrom);
        PageInfo<TbFault> pageInfo = new PageInfo<TbFault>(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", pageInfo.getList());
        map.put("size", pageInfo.getTotal());
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 删除告警中心
     *
     * @param id :
     * @return com.zhansheng.framework.model.response.ResponseResult
     * @Author xufu
     * @Date 2019/9/30 14:33
     */
    @Override
    public ResponseResult deleteById(int id) {
        int i = fanFaultMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.DELETE_ERRO);
    }


    /**
     * 修改告警中心
     *
     * @param fault :
     * @return com.zhansheng.framework.model.response.ResponseResult
     * @Author xufu
     * @Date 2019/9/30 14:34
     */
    @Override
    public ResponseResult updateFanFault(TbFault fault) {
        TbFault tbFanFault = fault;
        int i = fanFaultMapper.updateByPrimaryKey(tbFanFault);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }


}
