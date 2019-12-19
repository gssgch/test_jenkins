package com.zhansheng.management_system.service;

import com.google.common.collect.Maps;
import com.zhansheng.api.model.IndustrialIndexControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFault;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbParticularsMysql;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.request.CommonListI;
import com.zhansheng.framework.domain.UserManager.TbDraughtfan;
import com.zhansheng.framework.domain.UserManager.TbLoop;
import com.zhansheng.framework.domain.UserManager.TbStateMysql;
import com.zhansheng.framework.domain.UserManager.TbWindmill;
import com.zhansheng.management_system.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/10/15 16:20
 */
@Service
@Transactional
public class IndustrialIndexServiceImpl implements IndustrialIndexControllerApi {


    @Autowired
    private TbFanFaultMapper faultMapper;


    /**
     * 查询所有故障数
     *
     * @return int
     * @Author xuzhengjie
     * @Date 2019/10/15 17:07
     */
    @Override
    public int findFaultCount() {
        int count = faultMapper.findFaultCount();
        return count;
    }


    /**
     * 查询20条故障数
     *
     * @return java.util.List<com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFault>
     * @Author xuzhengjie
     * @Date 2019/10/15 17:08
     */
    @Override
    public List<TbFault> findFault() {
        return faultMapper.findFault();
    }


    @Autowired
    private TbLoopMapper loopMapper;
    @Autowired
    private TbDraughtfanMapper draughtfanMapper;
    @Autowired
    private TbStateMapper stateMapper;
    @Autowired
    private TbParticularsMapper particularsMapper;
    @Autowired
    private TbWindmillMapper windmillMapper;

    /**
     * 查询各个模型
     *
     * @param commonList :
     * @return com.zhansheng.framework.domain.Results.Result
     * @Author xuzhengjie
     * @Date 2019/10/18 10:09
     */
    @Override
    public Result findByState(CommonListI commonList) {
        //获取当前系统
        Integer code = commonList.getCode();
        Map<String, Object> map = Maps.newHashMap();
        //查询风场id
        int windmillId = windmillMapper.findByWindmillId();
        TbWindmill windmillName = windmillMapper.findByWindmillName();
        //如果传了风厂Id 就查询这个风厂下的环路
        List<TbLoop> list = loopMapper.findPageList(windmillId);

        TbStateMysql stateList = null;
        Integer state1Value = null;
        try {
            for (TbLoop tbLoop : list) {
                //拿到环路id
                Integer loopId = tbLoop.getFLoopId();
                //查询这个环路下的所有风机
                List<TbDraughtfan> idList = draughtfanMapper.findByLoopIdList(loopId);
                tbLoop.setTbDraughtList(idList);
                //查询环路状态 并且以时间降序排序 取风机总数
                //通过这个环路查询所有风机的状态
                //风机总数
                int i = loopMapper.countLoop(loopId);
                tbLoop.setDraughtCount(i);
                //正常
                int state_1 = 0;
                //告警
                int state_2 = 0;
                //维修
                int state_3 = 0;
                for (TbDraughtfan draught : idList) {
                    Integer draught_number = draught.getFDraughtNumber();
                    //通过风机编号查询风机状态 并且以时间降序排序
                    stateList = stateMapper.findState1List(draught_number);
                    //工控系统
                    if (code == 1) {
                        state1Value = stateList.getFState1Value();
                    }
                    //叶片系统
                    if (code == 2) {
                        state1Value = stateList.getFState2Value();
                    }
                    //振动
                    if (code == 3) {
                        state1Value = stateList.getFState3Value();
                    }
                    if (state1Value == 1) {
                        state_1++;
                    }
                    if (state1Value == 2) {
                        state_2++;
                    }
                    if (state1Value == 3) {
                        state_3++;
                    }
                }
                tbLoop.setState_1(state_1);
                tbLoop.setState_2(state_2);
                tbLoop.setState_3(state_3);
                //查询总风机数
                for (TbDraughtfan draughtfan : idList) {
                    Integer draughtNumber = draughtfan.getFDraughtNumber();
                    TbStateMysql states = stateMapper.findState1List(draughtNumber);
                    //通过风机number查询 风机状态 和 风机详情
                    if (states == null) {
                        states = new TbStateMysql();
                        states.setF(0);
                    }
                    draughtfan.setTbState1(states);
                }
                //通过风机number查询风机详情
                for (TbDraughtfan draughtfan : idList) {
                    Integer draughtNumber = draughtfan.getFDraughtNumber();
                    //拿到风机表后台管理数据 去查询实时的风机状态
                    TbParticularsMysql particularsList = particularsMapper.particularOne(draughtNumber);
                    draughtfan.setTbParticulars1(particularsList);
                }
            }
        } catch (Exception e) {
        }
        map.put("row", list);
        map.put("windmillName", windmillName);
        return new Result(true, StatusCode.OK, "查询成功!", map);
    }




}
