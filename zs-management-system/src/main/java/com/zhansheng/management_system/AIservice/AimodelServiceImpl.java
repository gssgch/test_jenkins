package com.zhansheng.management_system.AIservice;

import com.zhansheng.api.aiManager.AimodelControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.*;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.AIdao.*;
import com.zhansheng.management_system.utils.AIConstant;
import com.zhansheng.management_system.utils.AIKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @author zhansheng
 * 模型表管理
 */
@Service
public class AimodelServiceImpl implements AimodelControllerApi {

    @Autowired
    private TbAImodelMapper tbAImodelMapper;

    @Autowired
    private TbAImodelColumnsMapper tbAImodelColumnsMapper;

    @Autowired
    private TbAItestResultMapper tbAItestResultMapper;

    @Autowired
    private TbAItrainResultMapper tbAItrainResultMapper;

    @Autowired
    private TbAImodelpreparamMapper tbAImodelpreparamMapper;

    @Autowired
    private TbAImodelpreresultMapper tbAImodelpreresultMapper;

    @Autowired
    private AikeysnumServiceImpl aiKeysnumService;

    @Autowired
    private AIKeyUtils aiKeyUtils;

    /**
     * 添加
     *
     * @param aiData :
     * @return com.zhansheng.framework.model.response.ResponseResult
     */
    @Override
    public ResponseResult addData(TbAImodel aiData) {
        TbAImodel tbAiData = aiData;
        //特征选取的key:算法的key
        String key = tbAiData.getFeatureselkey() + ":" + tbAiData.getAlgokey() + ":";
        Result result = aiKeysnumService.getNumByKey(key);
        if (result.getData() == null) {
            //更新index表（更新后的index值）冒号分割
            aiKeyUtils.insertIndexmh(tbAiData.getFeatureselkey(), tbAiData.getAlgokey());
        }

        //迭代次数字符串
        String resultkey = new DecimalFormat("00000000").format(tbAiData.getIternum() + 1);
        tbAiData.setModelkey(key + resultkey);
        tbAiData.setCreatetime(new Date());
        //设置迭代次数
        tbAiData.setIternum(tbAiData.getIternum() + 1);
        //设置模型存储位置
        tbAiData.setModelpath(AIConstant.AI_KEY_CONVERING + "/" + key + resultkey);
        tbAiData.setAvailable(1);
        int i = tbAImodelMapper.insert(tbAiData);
        if (i == 1) {
            //更新index表（更新后的index值）冒号分割
            //aiKeyUtils.updateIndexmh(tbAiData.getFeatureselkey(), tbAiData.getAlgokey(), result.getData().toString());
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 添加模型预测参数表
     *
     * @param aiData :
     * @return com.zhansheng.framework.model.response.ResponseResult
     */
    @Override
    public Result addDatamodelpreparam(TbAImodelpreparam aiData) {
        TbAImodelpreparam tbAiData = aiData;
        Result result = aiKeysnumService.getNumByKey(tbAiData.getTopic() + AIConstant.AI_PARAM);
        tbAiData.setParamkey(result.getData().toString());
        int i = tbAImodelpreparamMapper.insert(tbAiData);
        if (i == 1) {
            //更新index表（更新后的index值）
            aiKeyUtils.updateIndex(tbAiData.getTopic(), AIConstant.AI_PARAM, result.getData().toString());
            //TODO:
            List<TbAImodelpreresult> list= tbAImodelpreresultMapper.findList(String.valueOf(new Random().nextInt(10)));
            Map<Object, Object> map = new HashMap<>();
            map.put("list", list);
            map.put("paramkey" , result.getData().toString());
            return new Result(true, StatusCode.OK, "模型预测参数保存成功，返回最终预测结果 !", map);
        }
        return new Result(true, StatusCode.OK, "操作失败 !");
    }

    /**
     * 查询（模型表）
     *
     * @param modelkey :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findList(String modelkey) {
        List<TbAImodel> list= tbAImodelMapper.findList(modelkey, null);
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 查询（模型表）
     *
     * @param topic
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findListByThreeKey(String topic, String featureselkey, String algokey) {
        List<TbAImodel> list= tbAImodelMapper.findListByThreeKey(topic, featureselkey, algokey);
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 查询（模型表15字段）
     *
     * @param modelkey :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findColumns(String modelkey) {
        List<TbAImodelcolumns> list= tbAImodelColumnsMapper.findColumns(modelkey);
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 通过专题查询（模型表）
     *
     * @param topic :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    //@Override
    public Result findListByKey(String topic) {
        List<TbAImodel> list= tbAImodelMapper.findList(null, topic);
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 查询（测试结果表）
     *
     * @param modelkey :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findListTestResult(String modelkey) {
        List<TbAItestResult> list= tbAItestResultMapper.findList(modelkey);
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 查询（训练结果表）
     *
     * @param modelkey :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findListTrainResult(String modelkey) {
        List<TbAItrainResult> list= tbAItrainResultMapper.findList(modelkey);
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 查询（模型预测参数表）
     *
     * @param paramkey :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findListModelpreparam(String paramkey) {
        List<TbAImodelpreparam> list= tbAImodelpreparamMapper.findList(paramkey);
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 查询（模型预测结果表）
     *
     * @param paramkey :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findListModelpreresult(String paramkey) {
        List<TbAImodelpreresult> list= tbAImodelpreresultMapper.findList(paramkey);
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
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
        int i = tbAImodelMapper.deleteByPrimaryKey(id);
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
    public ResponseResult updateData(TbAImodel aiData) {
        TbAImodel tbData = aiData;
        int i = tbAImodelMapper.updateByPrimaryKey(tbData);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
