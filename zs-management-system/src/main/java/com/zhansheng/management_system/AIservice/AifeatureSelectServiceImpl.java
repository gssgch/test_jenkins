package com.zhansheng.management_system.AIservice;

import com.zhansheng.api.aiManager.AifeatureSelectControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbAIfeatureSelect;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.AIdao.TbAIfeatureSelectMapper;
import com.zhansheng.management_system.utils.AIConstant;
import com.zhansheng.management_system.utils.AIKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhansheng
 */
@Service
public class AifeatureSelectServiceImpl implements AifeatureSelectControllerApi {

    @Autowired
    private TbAIfeatureSelectMapper tbAIfeatureSelectMapper;

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
    public Result addData(TbAIfeatureSelect aiData) {
        TbAIfeatureSelect tbAiData = aiData;
        Result result = aiKeysnumService.getNumByKey(tbAiData.getTopic() + AIConstant.AI_FEATURESELECT);
        tbAiData.setFeatureselkey(result.getData().toString());
        tbAiData.setCreatetime(new Date());
        int i = tbAIfeatureSelectMapper.insert(tbAiData);
        if (i == 1) {
            //更新index表（更新后的index值）
            aiKeyUtils.updateIndex(tbAiData.getTopic(), AIConstant.AI_FEATURESELECT, result.getData().toString());
            return new Result(true, StatusCode.OK, "特征选取保存成功 !", result.getData().toString());
        }
        return new Result(true, StatusCode.OK, "操作失败 !");
    }

    /**
     * 查询（特征选取表）
     *
     * @param featureselkey :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findList(String featureselkey) {
        List<TbAIfeatureSelect> list= tbAIfeatureSelectMapper.findList(featureselkey, null);
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 通过专题查询（特征选取表）
     *
     * @param topic :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findListBytopic(String topic) {
        List<TbAIfeatureSelect> list= tbAIfeatureSelectMapper.findList(null, topic);
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
        int i = tbAIfeatureSelectMapper.deleteByPrimaryKey(id);
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
    public ResponseResult updateData(TbAIfeatureSelect aiData) {
        TbAIfeatureSelect tbData = aiData;
        int i = tbAIfeatureSelectMapper.updateByPrimaryKey(tbData);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
