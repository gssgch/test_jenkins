package com.zhansheng.management_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.Industrialcontrol.FaulttypeControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultLoc;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultType;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.dao.TbFaultTypeMapper;
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
 * @description
 * @date 2019/9/29 17:49
 */

@Service
@Transactional
public class TbFaulttypeServiceImpl implements FaulttypeControllerApi {


    @Autowired
    private TbFaultTypeMapper faultTypeMapper;


    @Autowired
    private RedisService redisService;

    //增删改同步redis数据
    private void getRedis() {
        //查询redis 是否存在
        List<TbFaultType> list =(List<TbFaultType>) redisService.getListByKey("fault_type", 0, -1);
        if (list == null) {
            //如果不存在 如果不存在重新查询 存入redis
            list = baseService.getTypeList();
        } else {
            //如果redis存在 删除 并重新写入 redis
            redisService.delObj("fault_type");
            list = faultTypeMapper.findList();
            for (TbFaultType type : list) {
                redisService.addList("fault_type", type);
            }
        }
    }


    @Override
    public ResponseResult addFaultType(TbFaultType faultType) {
        TbFaultType tbFaultType = faultType;
        int i = faultTypeMapper.insert(tbFaultType);
        if (i == 1) {
            getRedis();
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }



    @Autowired
    private BaseServiceImpl baseService;

    @Override
    public Result findList(PageParam pageParam) {
        if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
//先查询redis中有没有数据
        Long i = redisService.getFindSize("fault_type");
        List<TbFaultType> list = null;
        if (i <= 0) {
            synchronized (this) {
                list = (List<TbFaultType>) redisService.getListByKey("fault_type",
                        pageParam.getSize() * (pageParam.getPage() - 1),
                        (pageParam.getPage() * pageParam.getSize()) - 1);
                if (list == null || list.size() <= 0) {
                    List<TbFaultType> list2 = faultTypeMapper.findList();
                    PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
                    List<TbFaultType> list1 = faultTypeMapper.findList();
                    PageInfo<TbFaultType> pageInfo = new PageInfo<TbFaultType>(list1);
                    for (TbFaultType type : list2) {
                        redisService.addList("fault_type", type);
                    }
                    Map<Object, Object> map = new HashMap<>();
                    map.put("page", pageInfo.getList());
                    map.put("size", pageInfo.getTotal());
                    return new Result(true, StatusCode.OK, "查询成功 !", map);
                } else {
                    //查询redis 数据库
                    Map<Object, Object> map = getRedisPage(pageParam);
                    return new Result(true, StatusCode.OK, "查询成功 !", map);
                }
            }
        } else {
            Map<Object, Object> map = getRedisPage(pageParam);
            return new Result(true, StatusCode.OK, "查询成功 !", map);
        }
    }

    private Map<Object, Object> getRedisPage(PageParam pageParam) {
        Long i;
        List<TbFaultType> list;//查询redis 数据库
        i = redisService.getFindSize("fault_type");
        list = (List<TbFaultType>) redisService.getListByKey("fault_type",
                pageParam.getSize() * (pageParam.getPage() - 1),
                (pageParam.getPage() * pageParam.getSize()) - 1);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", list);
        map.put("size", i);
        return map;
    }


    //增删改同步redis数据
    @Override
    public ResponseResult deleteById(Integer id) {
        int i = faultTypeMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            getRedis();
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.DELETE_ERRO);
    }


    @Override
    public ResponseResult updateFaultType(TbFaultType faultType) {
        TbFaultType tbFaultType = faultType;
        int i = faultTypeMapper.updateByPrimaryKey(tbFaultType);
        if (i == 1) {
            getRedis();
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);

    }
}
/*if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<TbFaultType> list = baseService.getTbFaultTypes();
        PageInfo<TbFaultType> pageInfo = new PageInfo<TbFaultType>(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", pageInfo.getList());
        map.put("size", pageInfo.getTotal());
        return new Result(true, StatusCode.OK, "查询成功 !", map);*/