package com.zhansheng.management_system.utils;

import org.codehaus.jackson.map.ser.std.StringSerializer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/11/6 10:10
 */

@Service
public class RedisService {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOpsObj;


    /**
     * 根据指定o获取Object
     *
     * @param o
     * @return
     */
    public Object getObj(Object o) {
        Object o1 = valOpsObj.get(o);
        if (o1 == null) {
            return o1;
        } else {
            return o1;
        }
    }

    /**
     * 设置obj缓存
     *
     * @param key
     * @param value
     */
    public void setObj(Object key, Object value) {

        valOpsObj.set(key, value);
    }

    /**
     * 删除Obj缓存
     *
     * @param o
     */
    public void delObj(Object o) {
        redisTemplate.delete(o);
    }


    /**
     * 添加对象到redis 里面的list中
     * redis中的 list 是双向的 所以添加的时候需要注意
     * rightPush 先进先出 leftPush 先进后出 这里 需要注意
     *
     * @param key  list 对应的key
     * @param list 需要存的对象
     */
    public void addList(Object key, Object obj) {
        redisTemplate.opsForList().rightPush(key, obj);
    }


    public Long getFindSize(String key) {
        return redisTemplate.opsForList().size(key);
    }


    /**
     * opsForList().range(key, start, end);  取范围值  redis里面的list下标从0开始
     * 流程 拿到key 对应的list 取 0 到 5  和 mysql的limt  类似 注意下标即可
     * 从redis list 里面的获取数据分页
     *
     * @param key   redis list 对应的key
     * @param start 开始下标
     * @param end   介绍下标
     * @return 返回list给前端
     */
    public List getListByKey(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }



    /**
     * AI_ALL_KEYS:  redis中存储所有key的 名称
     *
     * @return
     */
    public Object findAINumByKey(String topicKey, String type) {
        if ("increment".equals(type)) {
            // 获取自增之后的值
            Long increment = redisTemplate.opsForHash().increment(AIConstant.AI_ALL_KEYS, topicKey, 1);
            System.out.println(increment);
            return redisTemplate.opsForHash().increment(AIConstant.AI_ALL_KEYS, topicKey, 1);
        } else if ("get".equals(type)) {
            return redisTemplate.opsForHash().get(AIConstant.AI_ALL_KEYS, topicKey);
        }else if("has".equals(type)){
            return redisTemplate.hasKey(AIConstant.AI_ALL_KEYS);
        }else{
            return null;
        }
    }

    /**
     * redis入库
     * @param topicKey
     * @param curnum
     */
    public void pushAIKeys(String topicKey, int curnum) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.opsForHash().put(AIConstant.AI_ALL_KEYS, topicKey,curnum);
    }
}
