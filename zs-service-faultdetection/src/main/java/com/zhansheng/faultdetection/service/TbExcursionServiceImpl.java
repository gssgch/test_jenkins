package com.zhansheng.faultdetection.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zhansheng.api.monitor.TbExcursionInterface;
import com.zhansheng.faultdetection.dao.BaseMapper;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.FaultDetection.CommonList;
import com.zhansheng.framework.domain.FaultDetection.TbParticulars;
import com.zhansheng.framework.domain.FaultDetection.TbState;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbDraughtfan;
import com.zhansheng.framework.domain.UserManager.TbLoop;
import com.zhansheng.framework.domain.UserManager.request.DraListDto;
import com.zhansheng.framework.domain.UserManager.response.WindmillCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @description
 * @date 2019/6/26
 */

@Service
@Transactional
public class TbExcursionServiceImpl implements TbExcursionInterface {

    @Autowired
    private BaseMapper baseMapper;
    @Autowired
    private BaseService baseService;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Result findPageList(CommonList commonList) {
        //获取叶片结冰监测List
        List<TbLoop> list = null;
        Map<String, Object> map = Maps.newHashMap();
        if (commonList.getWindmillId() > 0 && commonList.getLoopId() == 0) {
            //分页参数
            if (commonList.getPage() <= 0) {
                commonList.setPage(1);
            }
            if (commonList.getSize() <= 0) {
                commonList.setSize(2);
            }
            PageHelper.startPage(commonList.getPage(), commonList.getSize());
            //如果传了环路Id 就查一个环路里面的所有风机
            list = baseMapper.findPageList(commonList.getWindmillId());
            if (list.isEmpty()) {
                return new Result(false, StatusCode.ERROR, "查询结果为空!");
            }
            PageInfo<TbLoop> pageInfo = new PageInfo<TbLoop>(list);
            list = pageInfo.getList();
            map.put("rowCount", pageInfo.getTotal());
        }
        if (commonList.getWindmillId() > 0 && commonList.getLoopId() > 0) {
            //分页参数
            if (commonList.getPage() <= 0) {
                commonList.setPage(1);
            }
            if (commonList.getSize() <= 0) {
                commonList.setSize(2);
            }
            PageHelper.startPage(commonList.getPage(), commonList.getSize());
            list = baseMapper.findByLoopIdAndList(commonList.getWindmillId(), commonList.getLoopId());
            PageInfo<TbLoop> pageInfo = new PageInfo<TbLoop>(list);
            map.put("rowCount", pageInfo.getTotal());
        }


        //查询所有环路
        try {
            for (TbLoop tbLoop : list) {
                //拿到环路id
                Integer loopId = tbLoop.getFLoopId();
                //查询这个环路下的所有风机
                List<TbDraughtfan> idList = baseMapper.findByLoopIdList(loopId);
                tbLoop.setTbDraughtList(idList);
                //查询环路状态 并且以时间降序排序 取风机总数
                //通过这个环路查询所有风机的状态
                //风机总数
                int i = baseMapper.countLoop(loopId);
                tbLoop.setDraughtCount(i);
                int state_0 = 0;
                int state_1 = 0;
                int state_2 = 0;
                int state_3 = 0;
                for (TbDraughtfan draught : idList) {
                    Integer draught_number = draught.getFDraughtNumber();
                    List<TbState> stateList = baseService.bladeState(draught_number);
                    //通过风机编号查询风机状态 并且以时间降序排序
                    for (TbState state : stateList) {
                        //状态
                        Integer fState1 = state.getFState4();
                        //叶片结冰状态
                        if (fState1 == 0) {
                            state_0++;
                        }
                        if (fState1 == 1) {
                            state_1++;
                        }
                        if (fState1 == 2) {
                            state_2++;
                        }
                        if (fState1 == 3) {
                            state_3++;
                        }
                    }
                }
                tbLoop.setState_0(state_0);
                tbLoop.setState_1(state_1);
                tbLoop.setState_2(state_2);
                tbLoop.setState_3(state_3);
                //查询总风机数
                for (TbDraughtfan draughtfan : idList) {
                    Integer draughtNumber = draughtfan.getFDraughtNumber();
                    //通过风机number查询 风机状态 和 风机详情
                    TbState states = baseService.excursionOne(draughtNumber);
                    if (states == null) {
                        states = new TbState();
                        states.setF(0);
                    }
                    draughtfan.setTbState(states);
                }
                //查询风机详情
                //30秒平均风速
                Double spd_30s = 0.00;
                //月发电量
                Integer griEnerM = 0;
                //通过风机number查询风机详情
                for (TbDraughtfan draughtfan : idList) {
                    Integer draughtNumber = draughtfan.getFDraughtNumber();
                    TbParticulars particularsList = baseService.particularOne(draughtNumber);
                    //通过风机Id查询风机详情
                    if (particularsList == null) {
                        particularsList = new TbParticulars();
                        particularsList.setFWindSpd30S(0.0);
                        particularsList.setFGriEnerm(0);
                    }

                    spd_30s += particularsList.getFWindSpd30S();
                    //拿到月发电量字段
                    griEnerM += particularsList.getFGriEnerm();
                    draughtfan.setTbParticulars(particularsList);
                }
                //只取小数点后两位
                BigDecimal bd = new BigDecimal(spd_30s / i);
                Double tem = bd.setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
                tbLoop.setTotalPower(griEnerM);
                tbLoop.setWind_spd(tem);
            }
        } catch (Exception e) {

        }
        map.put("row", list);
        return new Result(true, StatusCode.OK, "查询成功!", map);
    }


    /**
     * 查询当前模型状态
     *
     * @param commonList
     * @return
     */
    @Override
    public Result findPageListNew(CommonList commonList) {
        //获取叶片结冰监测List
        List<TbLoop> list = null;
        Map<String, Object> map = Maps.newHashMap();
        if (commonList.getWindmillId() > 0 && commonList.getLoopId() == 0) {
            //分页参数
            if (commonList.getPage() <= 0) {
                commonList.setPage(1);
            }
            if (commonList.getSize() <= 0) {
                commonList.setSize(2);
            }
            PageHelper.startPage(commonList.getPage(), commonList.getSize());
            //如果传了环路Id 就查一个环路里面的所有风机
            list = baseMapper.findPageList(commonList.getWindmillId());
            if (list.isEmpty()) {
                return new Result(false, StatusCode.ERROR, "查询结果为空!");
            }
            PageInfo<TbLoop> pageInfo = new PageInfo<TbLoop>(list);
            list = pageInfo.getList();
            map.put("rowCount", pageInfo.getTotal());
        }
        if (commonList.getWindmillId() > 0 && commonList.getLoopId() > 0) {
            //分页参数
            if (commonList.getPage() <= 0) {
                commonList.setPage(1);
            }
            if (commonList.getSize() <= 0) {
                commonList.setSize(2);
            }
            PageHelper.startPage(commonList.getPage(), commonList.getSize());
            list = baseMapper.findByLoopIdAndList(commonList.getWindmillId(), commonList.getLoopId());
            PageInfo<TbLoop> pageInfo = new PageInfo<TbLoop>(list);
            map.put("rowCount", pageInfo.getTotal());
        }


        //查询所有环路
        for (TbLoop tbLoop : list) {
            //拿到环路id
            Integer loopId = tbLoop.getFLoopId();
            //查询这个环路下的所有风机
            List<TbDraughtfan> idList = baseMapper.findByLoopIdList(loopId);
            tbLoop.setTbDraughtList(idList);
            //查询环路状态 并且以时间降序排序 取风机总数
            //通过这个环路查询所有风机的状态
            //风机总数
            int i = baseMapper.countLoop(loopId);
            tbLoop.setDraughtCount(i);
            int state_0 = 0;
            int state_1 = 0;
            int state_2 = 0;
            for (TbDraughtfan draught : idList) {
                Integer draught_number = draught.getFDraughtNumber();
                List<TbState> stateList = baseService.bladeState(draught_number);
                //通过风机编号查询风机状态 并且以时间降序排序
                for (TbState state : stateList) {
                    //状态
                    Integer fState1 = state.getFState4();
                    //叶片结冰状态
                    if (fState1 == -1) {
                        state_0++;
                        draught.setState(-1);
                    }
                    if (fState1 == 1) {
                        state_1++;
                        draught.setState(1);
                    }
                    if (fState1 == 2) {
                        state_2++;
                        draught.setState(2);
                    }
                }
            }
            tbLoop.setState_0(state_0);
            tbLoop.setState_1(state_1);
            tbLoop.setState_2(state_2);

            //查询风机详情
            //30秒平均风速
            Double spd_30s = 0.00;
            //月发电量
            Integer griEnerM = 0;
            //通过风机number查询风机详情
            for (TbDraughtfan draughtfan : idList) {
                Integer draughtNumber = draughtfan.getFDraughtNumber();
                TbParticulars particularsList = baseService.particularOne(draughtNumber);
                //通过风机Id查询风机详情
                if (particularsList == null) {
                    particularsList = new TbParticulars();
                    particularsList.setFWindSpd30S(0.0);
                    particularsList.setFGriEnerm(0);
                }
                spd_30s += particularsList.getFWindSpd30S();
                //拿到月发电量字段
                griEnerM += particularsList.getFGriEnerm();
                draughtfan.setTbParticulars(particularsList);
            }
            //只取小数点后两位
            BigDecimal bd = new BigDecimal(spd_30s / i);
            Double tem = bd.setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
            tbLoop.setTotalPower(griEnerM);
            tbLoop.setWind_spd(tem);
        }
        Integer state_0 = 0;
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
        winList.setWindSpd(spd);


        map.put("row", list);
        map.put("winList", winList);
        return new Result(true, StatusCode.OK, "查询成功!", map);
    }





    /**
     * 曲线图多条件查询
     *
     * @param draListDto
     * @return
     */
    @Override
    public Result findNumberListNew(DraListDto draListDto) {
        List list = new ArrayList();

        List<TbParticulars> numberList = findNumberListNew1(draListDto);
        int index = 0;
        for (int i = 0; i < 70; i++) {
            TbParticulars particulars = numberList.get(index += 36);
            list.add(particulars);
        }

        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "操作成功 !", map);
    }

    public List<TbParticulars> findNumberListNew1(DraListDto draListDto) {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fGridPower", true);
        fieldsObject.put("fWindSpd30S", true);
        fieldsObject.put("fWindSpeed", true);
        fieldsObject.put("fWindDirect", true);
        fieldsObject.put("fWindDir60s", true);
        fieldsObject.put("fWindDir10m", true);
        fieldsObject.put("fDateAcqTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fDraughtNumber").is(draListDto.getDraughtNumber())).limit(2880);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fDateAcqTime")));
        return mongoTemplate.find(query, TbParticulars.class);
    }

}
