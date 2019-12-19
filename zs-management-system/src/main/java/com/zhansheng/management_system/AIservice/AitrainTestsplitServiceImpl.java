package com.zhansheng.management_system.AIservice;

import com.zhansheng.api.aiManager.AitrainTestsplitControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbAItrainTestsplit;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.AIdao.TbAItrainTestsplitMapper;
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
public class AitrainTestsplitServiceImpl implements AitrainTestsplitControllerApi {

    @Autowired
    private TbAItrainTestsplitMapper tbAItrainTestsplitMapper;

    /**
     * 添加
     *
     * @param aiData :
     * @return com.zhansheng.framework.model.response.ResponseResult
     */
    @Override
    public Result addData(TbAItrainTestsplit aiData) {
        TbAItrainTestsplit tbAiData = aiData;
        tbAiData.setCreatetime(new Date());
        int i = tbAItrainTestsplitMapper.insert(tbAiData);
        if (i == 1) {
            return new Result(true, StatusCode.OK, "训练集测试集切分数据保存成功 !");
        }
        return new Result(true, StatusCode.OK, "操作失败 !");
    }

    /**
     * 查询（训练集测试集切分数据表）
     *
     * @param featureselkey :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findList(String featureselkey) {
        List<TbAItrainTestsplit> list= tbAItrainTestsplitMapper.findList(featureselkey);
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
        int i = tbAItrainTestsplitMapper.deleteByPrimaryKey(id);
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
    public ResponseResult updateData(TbAItrainTestsplit aiData) {
        TbAItrainTestsplit tbData = aiData;
        int i = tbAItrainTestsplitMapper.updateByPrimaryKey(tbData);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
