package com.zhansheng.faultdetection.service;

import com.google.common.collect.Maps;
import com.zhansheng.api.monitor.TbIndexInterface;
import com.zhansheng.faultdetection.dao.BaseMapper;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.FaultDetection.TbParticulars;
import com.zhansheng.framework.domain.FaultDetection.TbState;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbDraughtfan;
import com.zhansheng.framework.domain.UserManager.TbLoop;
import com.zhansheng.framework.domain.UserManager.response.WindmillCount;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @description
 * @date 2019/6/21
 */
@Service
@Transactional
public class TbIndexServiceImpl implements TbIndexInterface {

    @Autowired
    private BaseMapper baseMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BaseService baseService;

    @Override
    public Result findPageListNew(int windmillId) {
        long startTime = System.currentTimeMillis();    //获取开始时间
        //1 先查询所有环路
        List<TbLoop> list = null;
        TbParticulars particular = null;
        Map<String, Object> map = Maps.newHashMap();
        Integer num = 5;

        if (windmillId > 0) {
            //首页
            //查询当前风厂下的所有环路
            list = baseMapper.findPageList(windmillId);
            if (list.isEmpty()) {
                return new Result(false, StatusCode.ERROR, "查询结果为空!");
            }
            map.put("rowCount", list.size());
        }


        //记录全场风机状态......
        Integer winMnormal = 0;//正常
        Integer winMfault = 0;//故障
        Integer winMstop = 0;//停机
        //记录风厂所有风机平均风速
        Double winspd_30s = 0.00;
        //记录风厂月发电量
        Integer wingriEnerM = 0;
        //2 通过环路信息查询各个风机的详情
        try {
            for (TbLoop tbLoop : list) {
                Integer loopId = tbLoop.getFLoopId();
                // 2.1定以各个状态(环路下调用)
                Integer Mnormal = 0;//正常
                Integer Mfault = 0;//故障
                Integer Mstop = 0;//停机
                // 2.2定义百分比

                // 2.2查询某一个环路下的所有风机
                List<TbDraughtfan> draughtfanList = baseMapper.findByLoopIdList(loopId);
                for (TbDraughtfan draughtfan : draughtfanList) {
                    // 2.3拿到风机编号
                    Integer number = draughtfan.getFDraughtNumber();
                    // 2.4查询对应所有的状态
                    TbState tbStates = baseService.indexState(number);
                    // 2.5获取各个模型状态值
                    Integer state1 = tbStates.getFState1();//叶根螺栓断裂
                    Integer state2 = tbStates.getFState2();//叶片结冰
                    Integer state4 = tbStates.getFState4();//偏航
                    Integer state3 = tbStates.getFState3();//振动
                    Integer state5 = tbStates.getFState5();//音视频
                    Integer fault = 0;//故障数
                    Double zore = 0.00;
                    // 2.6查询各个环路下的所有风机状态记录数
                    // 2.61先判断停机状态
                    if (state1 == -1 && state2 == -1 && state4 == -1 && state3 == -1 && state5 == -1) {
                        Mstop++;
                        winMstop++;
                        zore = -1.00;//停机
                        draughtfan.setState(-1);
                    }
                    // 2.62判断正常
                    if (state1 == 1 && state2 == 1 && state4 == 1 && state3 == 1 && state5 == 1) {
                        Mnormal++;
                        winMnormal++;
                        zore = 1.00;
                        draughtfan.setState(1);
                    } else if (state1 != -1 && state2 != -1 && state4 != -1 && state3 != -1 && state5 != -1) {
                        Mfault++;
                        winMfault++;
                        draughtfan.setState(2);
                    }

                    // 2.63判断模型进度条
                    if (zore == -1) {
                        draughtfan.setPercent(zore);
                    }
                    if (zore == 1) {
                        draughtfan.setPercent(zore);
                    }
                    if (state1 == 2) {
                        fault++;
                    }
                    if (state2 == 2) {
                        fault++;
                    }
                    if (state4 == 2) {
                        fault++;
                    }
                    if (state3 == 2) {
                        fault++;
                    }
                    if (state5 == 2) {
                        fault++;
                    }
                    if (fault != 0) {
                        draughtfan.setPercent(1 - ((double) fault / (double) num));
                    }
                    // 2.7查询详情
                    TbParticulars tbParticulars = baseService.particularOne(number);
                    if (tbParticulars == null){

                    }
                    draughtfan.setTbParticulars(tbParticulars);
                }
                // 2.8写入当前环路状态
                tbLoop.setState_0(Mnormal);//正常
                tbLoop.setState_1(Mfault);//故障
                tbLoop.setState_2(Mstop);//停机
                // 2.9查询当前环路下的总风机数
                int i = baseMapper.countLoop(tbLoop.getFLoopId());
                Double spd_30s = 0.00;
                //月发电量
                Integer griEnerM = 0;
                //通过风机number查询风机详情
                for (TbDraughtfan draughtfan : draughtfanList) {
                    TbParticulars particulars = draughtfan.getTbParticulars();
                    //拿到单个风机30秒风速(环路计算)
                    spd_30s += particulars.getFWindSpd30S();
                    //拿到月发电量字段(环路计算)
                    griEnerM += particulars.getFGriEnerm();
                    //拿到单个风机30秒风速(风厂计算)
                    winspd_30s += particulars.getFWindSpd30S();
                    //拿到月发电量字段(风厂计算)
                    wingriEnerM += particulars.getFGriEnerm();
                }
                if (!draughtfanList.isEmpty()) {
                    tbLoop.setTbDraughtList(draughtfanList);
                }
                //只取小数点后两位
                BigDecimal bd = new BigDecimal(spd_30s / i);
                Double tem = bd.setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
                tbLoop.setDraughtCount(i);//
                tbLoop.setTotalPower(griEnerM);
                tbLoop.setWind_spd(tem);
            }
        } catch (Exception e) {

        }


        System.out.println("------------------ Double winspd_30s = " + winspd_30s);
        System.out.println("------------------ Integer wingriEnerM = " + wingriEnerM);
        /*Integer state_0 = 0;
        Integer state_1 = 0;
        Integer state_2 = 0;
        Integer count = 0;
        Integer totalPower = 0;
        Double windSpd = 0.00;
        for (TbLoop loop : list) {
            state_0 = state_0 + loop.getState_0();//正常
            state_1 = state_1 + loop.getState_1();//故障
            state_2 = state_2 + loop.getState_2();//故障
            count = count + loop.getDraughtCount();//当前环路下风机总数
            totalPower = totalPower + loop.getTotalPower();//当前环路有功功率
            windSpd = windSpd + loop.getWind_spd();//风速
        }
        int size = list.size();
       WindmillCount winList = new WindmillCount();
        winList.setCount(count);
        winList.setState_0(state_0);
        winList.setState_1(state_1);
        winList.setState_2(state_2);
        winList.setTotalPower(totalPower);
        BigDecimal bd = new BigDecimal(windSpd / list.size());
        Double spd = bd.setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
        winList.setWindSpd(spd);*/
        int dra = baseMapper.countDra();
        //记录全风厂风机状态......
        WindmillCount winList = new WindmillCount();
        winList.setState_0(winMnormal);//记录正常数(模型状态)
        winList.setState_1(winMfault);
        winList.setState_2(winMstop);
        winList.setTotalPower(wingriEnerM);//全风厂有功功率
        BigDecimal bd = new BigDecimal(winspd_30s / dra);
        Double spd = bd.setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
        winList.setWindSpd(spd);//全场平均风速
        winList.setCount(dra);//全风厂风机数


        map.put("row", list);
        map.put("winList", winList);
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 查询模型5个状态
     *
     * @param number
     * @return
     */
    @Override
    public Result findModel(int number) {
        TbState state = baseService.indexState(number);
        Map<String, Object> map = Maps.newHashMap();
        map.put("state", state);
        return new Result(true, StatusCode.OK, "操作成功 ! ", map);
    }







/*
    public Result findPageList(int windmillId) {
        List<TbLoop> list = null;
        TbParticulars particular = null;
        Map<String, Object> map = Maps.newHashMap();

        if (windmillId > 0) {
            //首页
            //查询当前风厂下的所有环路
            list = baseMapper.findPageList(windmillId);
            for (TbLoop tbLoop : list) {

            }
            if (list.isEmpty()) {
                return new Result(false, StatusCode.ERROR, "查询结果为空!");
            }
            map.put("rowCount", list.size());
        }
        for (TbLoop s : list) {
            Integer loopId = s.getFLoopId();
            int state1Count = 0;
            int state2Count = 0;
            int state3Count = 0;
            int state4Count = 0;
            //int draughtCount = 0;

            List<TbDraughtfan> idList = baseMapper.findByLoopIdList(loopId);
            for (TbDraughtfan draughtfan : idList) {
                Integer draughtNumber = draughtfan.getFDraughtNumber();
                //通过风机编号叶片结冰状态和叶根螺栓断裂状态
                //拿到状态信息
                List<TbState> bladeList = baseService.monitorList(draughtNumber);
                for (TbState state : bladeList) {
                    //draughtfan.setState(state);
                    //叶片结冰状态 0 1 2 3
                    Integer fState2 = state.getFState2();
                    //叶片结冰进度条
                    Double progressState2 = state.getFProgressState2();
                    //拿到的是dobblo类型不能比较要 用整数比较
                    int v2 = (int) (progressState2 * 1000);
                    //叶根螺栓断裂状态 0 1 2 3
                    Integer fState1 = state.getFState1();
                    //叶根螺栓进度条
                    Double progressState1 = state.getFProgressState1();
                    int v1 = (int) (progressState1 * 1000);

                    //查询风机详情 通过风机number
                    particular = baseService.particularOne(draughtNumber);
                    //draughtfan.setTbParticulars(particular);

                    //叶根螺栓断裂  <= 叶片结冰
                    if (fState1 <= fState2) {
                        //如果叶片结冰  == 叶根螺栓断裂
                        if (fState1 == fState2) {
                            if (v1 < v2) {
                                particular.setState(fState2);
                                particular.setFProgressState(progressState2);
                                particular.setState(2);
                            } else {
                                particular.setFWtState(fState1);
                                particular.setFProgressState(progressState1);
                                particular.setState(1);
                            }
                        } else {
                            particular.setFWtState(fState2);
                            particular.setFProgressState(progressState2);
                            particular.setState(2);
                        }
                        //否则就是叶根螺栓 >= 叶片结冰的状态  就返回跟螺栓断裂
                    } else {
                        particular.setFWtState(fState1);
                        particular.setFProgressState(progressState1);
                        particular.setState(1);
                    }
                    try {
                        draughtfan.setTbParticulars(particular);
                        Integer fWtState = particular.getFWtState();
                        // 风机各状态数量统计
                        if (fWtState == 0) {
                            state1Count++;
                        }
                        if (fWtState == 1) {
                            state2Count++;
                        }
                        if (fWtState == 2) {
                            state3Count++;
                        }
                        if (fWtState == 3) {
                            state4Count++;
                        }

                    } catch (Exception e) {
                    }
                }

                // 当前环路总风机数量统计
                // draughtCount++;

            }
            s.setState_0(state1Count);
            s.setState_1(state2Count);
            s.setState_2(state3Count);
            s.setState_3(state4Count);

            int i = baseMapper.countLoop(s.getFLoopId());
            Double spd_30s = 0.00;
            //月发电量
            Integer griEnerM = 0;
            //通过风机number查询风机详情
            for (TbDraughtfan draughtfan : idList) {
                Integer draughtNumber = draughtfan.getFDraughtNumber();
                List<TbParticulars> particularsList = baseService.particular(draughtNumber);
                for (TbParticulars particulars : particularsList) {
                    //拿到单个风机30秒风速
                    spd_30s += particulars.getFWindSpd30S();
                    //拿到月发电量字段
                    griEnerM += particulars.getFGriEnerm();
                }
            }
            if (!idList.isEmpty()) {
                s.setTbDraughtList(idList);
            }

            //只取小数点后两位
            BigDecimal bd = new BigDecimal(spd_30s / i);
            Double tem = bd.setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
            s.setDraughtCount(i);
            s.setTotalPower(griEnerM);
            s.setWind_spd(tem);
        }
        map.put("row", list);
        return new Result(true, StatusCode.OK, "查询成功!", map);
    }
*/


}