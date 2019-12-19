package com.zhansheng.management_system.service;

import com.zhansheng.api.Industrialcontrol.FanFaultAlarmControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanfaultalarm;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.dao.TbFanfaultalarmMapper;
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
 * @date 2019/10/11 15:32
 */

@Service
@Transactional
public class TbFanFaultAlarmServiceImpl implements FanFaultAlarmControllerApi {

    @Autowired
    private TbFanfaultalarmMapper fanfaultalarmMapper;

    /**
     * @param fanfaultalarm :
     * @return com.zhansheng.framework.model.response.ResponseResult
     * @Author xuzhengjie
     * @Date 2019/10/11 15:51
     */
    @Override
    public ResponseResult addFanfaultalarm(TbFanfaultalarm fanfaultalarm) {
        TbFanfaultalarm tbFanfaultalarm = fanfaultalarm;
        tbFanfaultalarm.setAlarmDate(new Date());
        tbFanfaultalarm.setStatus(0);
        int i = fanfaultalarmMapper.insert(tbFanfaultalarm);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * @return com.zhansheng.framework.domain.Results.Result
     * @Author xuzhengjie
     * @Date 2019/10/11 15:51
     */
    @Override
    public Result findList() {
        List<TbFanfaultalarm> list = fanfaultalarmMapper.findList();
        Map map = new HashMap();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }
}
