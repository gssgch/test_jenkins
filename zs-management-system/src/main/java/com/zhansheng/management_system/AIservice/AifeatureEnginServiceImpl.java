package com.zhansheng.management_system.AIservice;

import com.zhansheng.api.aiManager.AifeatureEnginControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbAIfeatureEngFanStat;
import com.zhansheng.framework.domain.aiManager.TbAIfeatureEngin;
import com.zhansheng.framework.domain.aiManager.TbAIrawfieldselect;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.AIdao.TbAIfeatureEngFanStatMapper;
import com.zhansheng.management_system.AIdao.TbAIfeatureEnginMapper;
import com.zhansheng.management_system.AIdao.TbAIrawfieldselectMapper;
import com.zhansheng.management_system.utils.AIConstant;
import com.zhansheng.management_system.utils.AIKeyUtils;
import com.zhansheng.management_system.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author zhansheng
 */
@Service
public class AifeatureEnginServiceImpl implements AifeatureEnginControllerApi {

    @Autowired
    private TbAIfeatureEnginMapper aIfeatureEnginMapper;

    @Autowired
    private TbAIfeatureEngFanStatMapper aIfeatureEngFanStatMapper;

    @Autowired
    private TbAIrawfieldselectMapper tbAIrawfieldselectMapper;

    @Autowired
    private AikeysnumServiceImpl aiKeysnumService;

    @Autowired
    private AIKeyUtils aiKeyUtils;

    @Autowired
    private FileUtils fileUtils;

    /**
     * 添加
     *
     * @param aiData :
     * @return com.zhansheng.framework.model.response.ResponseResult
     */
    @Override
    public Result addData(TbAIfeatureEngin aiData) throws IOException {
        TbAIfeatureEngin tbAiData = aiData;
        Result result = aiKeysnumService.getNumByKey(tbAiData.getTopic() + AIConstant.AI_FEATUREENG);
        tbAiData.setFeaturekey(result.getData().toString());
        tbAiData.setCreatetime(new Date());
        int i = aIfeatureEnginMapper.insert(tbAiData);
        if (i == 1) {
            //更新index表（更新后的index值）
            aiKeyUtils.updateIndex(tbAiData.getTopic(), AIConstant.AI_FEATUREENG, result.getData().toString());
            String[] num = tbAiData.getFannums().split(",");
            for(String a:num) {
                TbAIfeatureEngFanStat tbAIfeatureEngFanStat = new TbAIfeatureEngFanStat();
                tbAIfeatureEngFanStat.setFeaturekey(result.getData().toString());
                tbAIfeatureEngFanStat.setFannum(a);
                //TODO:目前count数随机，客户看完再改
                tbAIfeatureEngFanStat.setCounts(new Random().nextInt(1000));
                aIfeatureEngFanStatMapper.insert(tbAIfeatureEngFanStat);
            }

            //保存python文件
            //fileUtils.write("D:\\workfile\\", tbAiData.getCoding());

            return new Result(true, StatusCode.OK, "特征工程保存成功 !");
        }
        return new Result(true, StatusCode.OK, "操作失败 !");
    }

    /**
     * 查询（特征工程表）
     *
     * @param featurekey :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findList(String featurekey) {
        List<TbAIfeatureEngin> list= aIfeatureEnginMapper.findList(featurekey, null);
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 通过专题查询（特征工程表）
     *
     * @param topic :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findListBytopic(String topic) {
        List<TbAIfeatureEngin> list= aIfeatureEnginMapper.findList(null, topic);
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 查询（特征工程表风机统计表）
     *
     * @param featurekey :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findListFan(String featurekey, String selectkey) {
        List<TbAIfeatureEngFanStat> list= aIfeatureEngFanStatMapper.findListFan(featurekey);
        List<TbAIrawfieldselect> timeList = tbAIrawfieldselectMapper.findList(selectkey);
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("timeList", timeList);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 删除
     *
     * @param id :
     * @return com.zhansheng.framework.model.response.ResponseResult
     */
    @Override
    public ResponseResult deleteById(Integer id) {
        int i = aIfeatureEnginMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.DELETE_ERRO);
    }


    /**
     * 修改
     *
     * @param aiData :
     * @return com.zhansheng.framework.model.response.ResponseResult
     */
    @Override
    public ResponseResult updateData(TbAIfeatureEngin aiData) {
        TbAIfeatureEngin tbData = aiData;
        int i = aIfeatureEnginMapper.updateByPrimaryKey(tbData);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
