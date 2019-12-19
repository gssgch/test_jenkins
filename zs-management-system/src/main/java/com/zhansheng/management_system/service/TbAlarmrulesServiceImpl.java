package com.zhansheng.management_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.Industrialcontrol.AlarmrulesControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmrules;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.dao.TbAlarmrulesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuzhengjie
 * @description 告警策略
 * @date 2019/9/29 13:44
 */
@Service
@Transactional
public class TbAlarmrulesServiceImpl implements AlarmrulesControllerApi {


    @Autowired
    private TbAlarmrulesMapper alarmrulesMapper;


    /**
     * 像告警策略添加数据
     *
     * @param alarmrules :
     * @return com.zhansheng.framework.model.response.ResponseResult
     * @Author xuzhengjie
     * @Date 2019/9/29 14:26
     */
    @Override
    public ResponseResult addAlarmrules(TbAlarmrules alarmrules) {
        TbAlarmrules tbAlarmrules = alarmrules;
        int i = alarmrulesMapper.insert(tbAlarmrules);
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
     * @Author xuzhengjie
     * @Date 2019/9/29 14:33
     */
    @Override
    public Result findList(PageParam pageParam) {
        if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<TbAlarmrules> list = alarmrulesMapper.findList();
        PageInfo<TbAlarmrules> pageInfo = new PageInfo<TbAlarmrules>(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", pageInfo.getList());
        map.put("size", pageInfo.getTotal());
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 删除告警策略
     *
     * @param id :
     * @return com.zhansheng.framework.model.response.ResponseResult
     * @Author xuzhengjie
     * @Date 2019/9/29 14:33
     */
    @Override
    public ResponseResult deleteById(int id) {
        int i = alarmrulesMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }


    /**
     * 修改告警策略
     *
     * @param alarmrules :
     * @return com.zhansheng.framework.model.response.ResponseResult
     * @Author xuzhengjie
     * @Date 2019/9/29 14:34
     */
    @Override
    public ResponseResult updateAlarmrules(TbAlarmrules alarmrules) {
        TbAlarmrules tbAlarmrules = alarmrules;
        int i = alarmrulesMapper.updateByPrimaryKey(tbAlarmrules);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }


}
