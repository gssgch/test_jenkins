package com.zhansheng.management_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.Industrialcontrol.AlarmreceiverControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmreceiver;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbAlarmrules;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultLoc;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.dao.TbAlarmreceiverMapper;
import com.zhansheng.management_system.dao.TbAlarmrulesMapper;
import com.zhansheng.management_system.utils.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuzhengjie
 * @description 告警处理人
 * @date 2019/9/27 17:28
 */

@Service
@Transactional
public class TbAlarmreceiverServiceImpl implements AlarmreceiverControllerApi {

    @Autowired
    private TbAlarmreceiverMapper alarmreceiverMapper;
    @Autowired
    private BaseServiceImpl baseService;
    @Autowired
    private RedisService redisService;


    //增删改同步redis数据
    private void getRedis() {
        //查询redis 是否存在
        List<TbAlarmreceiver> list = (List<TbAlarmreceiver>) redisService.getListByKey("alarmreceiver", 0, -1);
        if (list == null || list.size() <= 0) {
            //如果不存在 如果不存在重新查询 存入redis
            list = baseService.getAlarmreceiverList();
        } else {
            //如果redis存在 删除 并重新写入 redis
            redisService.delObj("alarmreceiver");
            list = alarmreceiverMapper.findList();
            for (TbAlarmreceiver alarmreceiver : list) {
                redisService.addList("alarmreceiver", alarmreceiver);
            }
        }
    }

    /**
     * 像告警处理人表添加数据
     *
     * @param alarmreceiver :
     * @return com.zhansheng.framework.model.response.ResponseResult
     * @Author xuzhengjie
     * @Date 2019/9/29 9:26
     */
    @Override
    public ResponseResult addAlarmreceiver(TbAlarmreceiver alarmreceiver) {
        TbAlarmreceiver tbAlarmreceiver = alarmreceiver;
        int i = alarmreceiverMapper.insert(tbAlarmreceiver);
        if (i == 1) {
            getRedis();
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 分页查询告警处理人数据
     *
     * @param pageParam :
     * @return com.zhansheng.framework.model.response.ResponseResult
     * @Author xuzhengjie
     * @Date 2019/9/29 9:27
     */
    @Override
    public Result findList(PageParam pageParam) {
        if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
        //先查询redis中有没有数据
        Long i = redisService.getFindSize("alarmreceiver");
        List<TbAlarmreceiver> list = null;
        if (i <= 0) {
            synchronized (this) {
                list = (List<TbAlarmreceiver>) redisService.getListByKey("alarmreceiver",
                        pageParam.getSize() * (pageParam.getPage() - 1),
                        (pageParam.getPage() * pageParam.getSize()) - 1);
                if (list == null || list.size() <= 0) {
                    List<TbAlarmreceiver> list2 = alarmreceiverMapper.findList();
                    PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
                    List<TbAlarmreceiver> list1 = alarmreceiverMapper.findList();
                    PageInfo<TbAlarmreceiver> pageInfo = new PageInfo<TbAlarmreceiver>(list1);
                    for (TbAlarmreceiver alarmreceiver : list2) {
                        redisService.addList("alarmreceiver", alarmreceiver);
                    }
                    Map<Object, Object> map = new HashMap<>();
                    map.put("page", pageInfo.getList());
                    map.put("size", pageInfo.getTotal());
                    return new Result(true, StatusCode.OK, "查询成功 !", map);
                } else {
                    //查询redis 数据库
                    Map<Object, Object> map = getRedisList(pageParam);
                    return new Result(true, StatusCode.OK, "查询成功 !", map);
                }
            }
        } else {
            //查询redis 数据库
            Map<Object, Object> map = getRedisList(pageParam);
            return new Result(true, StatusCode.OK, "查询成功 !", map);
        }

    }
    private Map<Object, Object> getRedisList(PageParam pageParam) {
        Long i;
        List<TbAlarmreceiver> list;
        i = redisService.getFindSize("alarmreceiver");
        list = (List<TbAlarmreceiver>) redisService.getListByKey("alarmreceiver",
                pageParam.getSize() * (pageParam.getPage() - 1),
                (pageParam.getPage() * pageParam.getSize()) - 1);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", list);
        map.put("size", i);
        return map;
    }




    @Autowired
    private TbAlarmrulesMapper alarmrulesMapper;

    /**
     * 从告警策略表查询当前的这个id是否在告警策略存在
     *
     * @param id :
     * @return com.zhansheng.framework.model.response.ResponseResult
     * @Author xuzhengjie
     * @Date 2019/9/29 11:29
     */
    @Override
    public ResponseResult deleteById(int id) {
        //查询告警策略 的邮件发送人的id
        List<TbAlarmrules> alarmreceiver = alarmrulesMapper.findByfkAlarmreceiver(id);
        if (alarmreceiver.size() <= 0) {
            int i = alarmreceiverMapper.deleteByPrimaryKey(id);
            if (i == 1) {
                getRedis();
                return new ResponseResult(CommonCode.SUCCESS);
            }
        }
        return new ResponseResult(CommonCode.DELETE_ERRO);
    }

    @Override
    public ResponseResult updateAlarmreceiver(TbAlarmreceiver alarmreceiver) {
        TbAlarmreceiver tbAlarmreceiver = alarmreceiver;
        int i = alarmreceiverMapper.updateByPrimaryKey(tbAlarmreceiver);
        if (i == 1) {
            getRedis();
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }
}
