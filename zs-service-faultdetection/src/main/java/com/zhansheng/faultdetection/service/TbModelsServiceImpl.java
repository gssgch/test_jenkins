package com.zhansheng.faultdetection.service;

import com.google.common.collect.Maps;
import com.zhansheng.faultdetection.dao.BaseMapper;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.FaultDetection.*;
import com.zhansheng.framework.domain.Results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author
 * @description
 * @date 2019/8/15
 */
@Service
public class TbModelsServiceImpl {


    /*@Autowired
    private BaseMapper baseMapper;


    @Autowired
    private BaseService baseService;


    *//**
     * 通过风厂id查询风机模型
      * @param modelsParamList
     * @return
     *//*
    public Result findTimeList(ModelsParamList modelsParamList) {
        //模型监测 正常List
        List<TbParticulars> normalList = null;
        //模型监测 不正常List
        List<TbParticulars> abnormalList = null;
        Map<String, Object> map = Maps.newHashMap();
        //查询风厂只有的风机编号
        //查询叶根螺栓断裂状态
        if (modelsParamList.getCode() == 1) {
            //查询风机正常的状态
            List<TbState> states = baseService.findfnormalState1();
            Random rand = new Random();
            //从备用数据库查询
            if (states.size() <= 0) {
                List<NormalParticulars> list = baseService.findYes();
                map.put("normalList", list);
            } else {
                TbState randomElement = states.get(rand.nextInt(states.size()));
                normalList = baseService.findParticulars(randomElement.getFDraughtNumber());
                map.put("normalList", normalList);
            }
            //不正常数据
            List<TbState> states1 = baseService.findabnormalfState1();
            if (states1.size() <= 0) {
                List<AbnormalParticulars> list = baseService.findNot();
                map.put("abnormalList", list);
            } else {
                TbState abnormal = states1.get(rand.nextInt(states1.size()));
                abnormalList = baseService.findParticulars(abnormal.getFDraughtNumber());
                map.put("abnormalList", abnormalList);
            }
        }


        //查询风机叶片结冰状态
        if (modelsParamList.getCode() == 2) {
            //查询风机正常的状态
            List<TbState> states = baseService.findfnormalState2();
            Random rand = new Random();
            //从备用数据库查询
            if (states.size() <= 0) {
                List<NormalParticulars> list = baseService.findYes();
                map.put("normalList", list);
            } else {
                TbState randomElement = states.get(rand.nextInt(states.size()));
                normalList = baseService.findParticulars(randomElement.getFDraughtNumber());
                map.put("normalList", normalList);
            }
            //不正常数据
            List<TbState> states1 = baseService.findabnormalfState2();
            if (states1.size() <= 0) {
                List<AbnormalParticulars> list = baseService.findNot();
                map.put("abnormalList", list);
            } else {
                TbState abnormal = states1.get(rand.nextInt(states1.size()));
                abnormalList = baseService.findParticulars(abnormal.getFDraughtNumber());
                map.put("abnormalList", abnormalList);
            }
        }

        //查询机组劣化状态
        if (modelsParamList.getCode() == 3) {

            //查询风机正常的状态
            List<TbState> states = baseService.findfnormalState3();
            Random rand = new Random();
            //从备用数据库查询
            if (states.size() <= 0) {
                List<NormalParticulars> list = baseService.findYes();
                map.put("normalList", list);
            } else {
                TbState randomElement = states.get(rand.nextInt(states.size()));
                normalList = baseService.findParticulars(randomElement.getFDraughtNumber());
                map.put("normalList", normalList);
            }
            //不正常数据
            List<TbState> states1 = baseService.findabnormalfState3();
            if (states1.size() <= 0) {
                List<AbnormalParticulars> list = baseService.findNot();
                map.put("abnormalList", list);
            } else {
                TbState abnormal = states1.get(rand.nextInt(states1.size()));
                abnormalList = baseService.findParticulars(abnormal.getFDraughtNumber());
                map.put("abnormalList", abnormalList);
            }


        }
        //查询偏航对风状态
        if (modelsParamList.getCode() == 4) {
            //查询风机正常的状态
            List<TbState> states = baseService.findfnormalState4();
            Random rand = new Random();
            //从备用数据库查询
            if (states.size() <= 0) {
                List<NormalParticulars> list = baseService.findYes();
                map.put("normalList", list);
            } else {
                TbState randomElement = states.get(rand.nextInt(states.size()));
                normalList = baseService.findParticulars(randomElement.getFDraughtNumber());
                map.put("normalList", normalList);
            }
            //不正常数据
            List<TbState> states1 = baseService.findabnormalfState4();
            if (states1.size() <= 0) {
                List<AbnormalParticulars> list = baseService.findNot();
                map.put("abnormalList", list);
            } else {
                TbState abnormal = states1.get(rand.nextInt(states1.size()));
                abnormalList = baseService.findParticulars(abnormal.getFDraughtNumber());
                map.put("abnormalList", abnormalList);
            }

        }
        return new Result(true, StatusCode.OK, "查询成功!", map);
    }*/

}
