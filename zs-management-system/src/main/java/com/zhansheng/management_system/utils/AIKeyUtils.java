package com.zhansheng.management_system.utils;

import com.zhansheng.framework.domain.aiManager.TbAIKeysnum;
import com.zhansheng.management_system.AIdao.TbAIkeysnumMapper;
import com.zhansheng.management_system.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

/**
 * Created by Huige
 * Email: 824203453@qq.com
 * DATE: 2019/12/4
 * Desc:
 * 模型可视化 各种数据表的Key生成规则
 */
@Service
public class AIKeyUtils {

    @Autowired
    private BaseServiceImpl baseService;

    @Autowired
    private TbAIkeysnumMapper tbAIKeysnumMapper;

    /**
     * 获取 模型可视化各个表的Key
     * @param topic
     * @param type
     * @return
     */
    public  String getAIKeys(int topic,String type){
        return baseService.findAINumByKey(topic+type);
    }

    public void updateIndex(String topic,String type, String result) {
        //更新index表（更新后的index值）
        String[] no = result.split("_");
        int resultNum = Integer.parseInt(no[2]) ;
        String s1=new DecimalFormat("00000000").format(resultNum);
        TbAIKeysnum tbAIKeysnum = new TbAIKeysnum();
        tbAIKeysnum.setPrefix(topic + type);
        tbAIKeysnum.setCurnum(s1);
        tbAIKeysnumMapper.updateByPrimaryKey(tbAIKeysnum);
    }

    public void updateIndexmh(String topic,String type, String result) {
        //更新index表（更新后的index值）
        String[] no = result.split(":");
        int resultNum = Integer.parseInt(no[2]) ;
        String s1=new DecimalFormat("00000000").format(resultNum);
        TbAIKeysnum tbAIKeysnum = new TbAIKeysnum();
        tbAIKeysnum.setPrefix(topic + ":" + type + ":");
        tbAIKeysnum.setCurnum(s1);
        tbAIKeysnumMapper.updateByPrimaryKey(tbAIKeysnum);
    }

    public void insertIndexmh(String topic,String type) {
        //更新index表（更新后的index值）
        TbAIKeysnum tbAIKeysnum = new TbAIKeysnum();
        tbAIKeysnum.setPrefix(topic + ":" + type + ":");
        tbAIKeysnum.setCurnum("00000002");
        tbAIKeysnumMapper.insert(tbAIKeysnum);
    }
}
