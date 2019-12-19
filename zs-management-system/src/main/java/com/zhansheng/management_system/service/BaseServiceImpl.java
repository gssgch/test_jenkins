package com.zhansheng.management_system.service;

import com.zhansheng.api.base.BaseControllerApi;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.*;
import com.zhansheng.framework.domain.aiManager.TbAIKeysnum;
import com.zhansheng.management_system.AIdao.TbAIkeysnumMapper;
import com.zhansheng.management_system.dao.*;
import com.zhansheng.management_system.utils.AIConstant;
import com.zhansheng.management_system.utils.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/9/29 14:10
 */

@Service
@Transactional
public class BaseServiceImpl implements BaseControllerApi {

    private static Boolean ISLOADAIKEYS = false;

    //添加接口调用 and 修改接口调用
    @Autowired
    private TbFaultTypeMapper faultTypeMapper;//获取告警类型
    @Autowired
    private TbFaultLocMapper faultLocMapper;//获取告警位置
    @Autowired
    private TbAlarmreceiverMapper alarmreceiverMapper;//获取告警处理人
    @Autowired
    private TbDictMapper dictMapper;//查询字典表
    @Autowired
    private TbFanfaultrepoMapper faultrpoMapper;//查询知识库
    @Autowired
    private TbParticularsMapper particularsMapper;//查询详情
    @Autowired
    private TbAIkeysnumMapper aiKeysnumMapper;// 模型可视化各表的主键数据


    //查询详情匹配知识库  并写入告警信息
    public void flag() {
        //1  先获取详情信息
        List<TbParticularsMysql> particularsList = particularsMapper.findList();
        //2 在获取告警知识库信息
        List<TbFanfaultrepo> faultrpoList = faultrpoMapper.findList();
        //3 遍历每一组数据
        for (TbParticularsMysql particulars : particularsList) {
            //全场无功功率
            String convertpower = particulars.getConvertpower();
            for (TbFanfaultrepo fanfaultrepo : faultrpoList) {
                //故障的条件表达式
                String exp = fanfaultrepo.getFaultExp();
                if (exp.indexOf("convertpower") != -1) {

                }
            }
        }
    }

    @Autowired
    RedisService redisService;

    //将告警处理人 存入redis
    public List<TbAlarmreceiver> getTbAlarmreceiver() {
        List<TbAlarmreceiver> list = (List<TbAlarmreceiver>) redisService.getListByKey("alarmreceiver", 0, -1);
        if (list == null || list.size() <= 0) {
            list = getAlarmreceiverList();
        }
        return list;
    }

    public List<TbAlarmreceiver> getAlarmreceiverList() {
        List<TbAlarmreceiver> list;
        synchronized (this) {
            list = (List<TbAlarmreceiver>) redisService.getListByKey("alarmreceiver", 0, -1);
            if (list == null || list.size() <= 0) {
                list = alarmreceiverMapper.findList();
                for (TbAlarmreceiver alarmreceiver : list) {
                    redisService.addList("alarmreceiver", alarmreceiver);
                }
            }
        }
        return list;
    }

    /**
     * 获取告警处理人
     *
     * @return java.util.List<com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmreceiver>
     * @Author xuzhengjie
     * @Date 2019/9/29 14:13
     */
    @Override
    public List<TbAlarmreceiver> findAlar() {
        return getTbAlarmreceiver();
    }


    //将故障位置 存入redis
    public List<TbFaultLoc> getTbFaultLocs() {
        List<TbFaultLoc> list = redisService.getListByKey("fault_loc", 0, -1);
        if (list == null || list.size() <= 0) {
            list = getLocList();
        }
        return list;
    }

    public List<TbFaultLoc> getLocList() {
        List<TbFaultLoc> list;
        synchronized (this) {
            list = redisService.getListByKey("fault_loc", 0, -1);
            if (list == null || list.size() <= 0) {
                list = faultLocMapper.findList();
                for (TbFaultLoc loc : list) {
                    redisService.addList("fault_loc", loc);
                }
            }
        }
        return list;
    }

    /**
     * 获取告警位置
     *
     * @return java.util.List<com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultLoc>
     * @Author xuzhengjie
     * @Date 2019/9/29 14:13
     */
    @Override
    public List<TbFaultLoc> findLoc() {
        return getTbFaultLocs();
    }


    //将 故障类型  数据存入redis
    public List<TbFaultType> getTbFaultTypes() {
        List<TbFaultType> list = (List<TbFaultType>) redisService.getListByKey("fault_type", 0, -1);
        if (list == null || list.size() <= 0) {
            list = getTypeList();
        }
        return list;
    }

    public List<TbFaultType> getTypeList() {
        List<TbFaultType> list;
        synchronized (this) {
            list = (List<TbFaultType>) (List<TbFaultType>) redisService.getListByKey("fault_type", 0, -1);
            if (list == null || list.size() <= 0) {
                list = faultTypeMapper.findList();
                for (TbFaultType type : list) {
                    redisService.addList("fault_type", type);
                }
            }
        }
        return list;
    }

    /**
     * 获取告警类型
     *
     * @return java.util.List<com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultType>
     * @Author xuzhengjie
     * @Date 2019/9/29 14:12
     */
    @Override
    public List<TbFaultType> findType() {
        return getTbFaultTypes();
    }


    /**
     * 通过key查询对应的value 并存入redis
     *
     * @param key :
     * @return java.util.List<com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbDictKey>
     * @Author xuzhengjie
     * @Date 2019/10/23 10:53
     */
    @Override
    public List<TbDictKey> findByKey(String key) {
        List<TbDictKey> list = (List<TbDictKey>) redisService.getListByKey("dict", 0, -1);
        List<TbDictKey> dictKeyList = new ArrayList<>();
        if (list == null || list.size() <= 0) {
            synchronized (this) {
                list = (List<TbDictKey>) (List<TbDictKey>) redisService.getListByKey("dict", 0, -1);
                if (list == null || list.size() <= 0) {
                    //解决缓存穿透 只允许一个请求查询数据库
                    list = dictMapper.findByList();
                    for (TbDictKey keyList : list) {
                        redisService.addList("dict", keyList);
                    }
                    return dictMapper.findByKey(key);
                }
            }
        } else {
            //查询缓存
            for (TbDictKey dictKey : list) {
                if (dictKey.getFDicttype().equals(key)) {
                    dictKeyList.add(dictKey);
                }
            }
        }
        return dictKeyList;
    }

    /**
     * 基本思路：
     * 每次系统启动  查询mysql数据库，写入redis 中，
     * 查询时，直接查询redis； 每次查询自增
     *
     * @return
     */
    @Override
    public String findAINumByKey(String topicKey) {

        // 从mysql---> redis
        if (!ISLOADAIKEYS) {
            aiKeyMysql2Redis();
        }
        // 自增取值
        Integer curnum = (Integer) redisService.findAINumByKey(topicKey, "increment");
        /*
         eg：101_orgslt_00000001   取值时自增1
             key的数字补位  int 转 String    1 --> 00000001
          */
        return topicKey + String.format(AIConstant.AI_KEY_CONVERING, curnum);
    }

    /*
      把 AI 各种key从mysql 写入到redis中
     */
    private void aiKeyMysql2Redis() {
        // 读mysql  写入到redis
        List<TbAIKeysnum> list = aiKeysnumMapper.findList();
        Boolean hasKey = (Boolean) redisService.findAINumByKey("","has");
        for (TbAIKeysnum ai : list) {
            String prefix = ai.getPrefix().trim();
            // 注意：转Int  mysql中数据:00000001  --->   redis中自增数据:1
            int mysqlCurnum = Integer.parseInt(ai.getCurnum());
            if (!hasKey) { // redis中没有 直接写入
                redisService.pushAIKeys(prefix, mysqlCurnum);
            } else { // 存在 则比较大小，再写
                // 取值
                int redisCurum = (Integer)redisService.findAINumByKey(prefix, "get");
                if (mysqlCurnum > redisCurum) {
                    redisService.pushAIKeys(prefix, mysqlCurnum);
                }
            }
        }
        // 加载完毕
        ISLOADAIKEYS = true;
    }
}
