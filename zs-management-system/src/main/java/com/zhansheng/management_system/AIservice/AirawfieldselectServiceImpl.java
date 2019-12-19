package com.zhansheng.management_system.AIservice;

import com.zhansheng.api.aiManager.AirawfieldselectControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.aiManager.TbAIrawfieldselect;
import com.zhansheng.framework.domain.aiManager.TbAIrawselectfanStat;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.AIdao.TbAIrawfieldselectMapper;
import com.zhansheng.management_system.AIdao.TbAIrawselectfanStatMapper;
import com.zhansheng.management_system.utils.AIConstant;
import com.zhansheng.management_system.utils.AIKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author zhansheng
 */
@Service
@Transactional
public class AirawfieldselectServiceImpl implements AirawfieldselectControllerApi {

    @Autowired
    private TbAIrawfieldselectMapper aIrawfieldselectMapper;

    @Autowired
    private TbAIrawselectfanStatMapper aIrawselectfanStatMapper;

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
    public Result addData(TbAIrawfieldselect aiData) {
        TbAIrawfieldselect tbAiData = aiData;
        Result result = aiKeysnumService.getNumByKey(tbAiData.getTopic() + AIConstant.AI_ORGSELECT);
        tbAiData.setSelectkey(result.getData().toString());
        tbAiData.setCreatetime(new Date());
        int i = aIrawfieldselectMapper.insert(tbAiData);
        if (i == 1) {
            //更新index表（更新后的index值）
            aiKeyUtils.updateIndex(tbAiData.getTopic(), AIConstant.AI_ORGSELECT, result.getData().toString());

            String[] num = tbAiData.getFannums().split(",");
            for(String a:num) {
                TbAIrawselectfanStat tbAIrawselectfanStat = new TbAIrawselectfanStat();
                tbAIrawselectfanStat.setSelectkey(result.getData().toString());
                tbAIrawselectfanStat.setFannum(a);
                //TODO:目前count数随机，客户看完再改
                tbAIrawselectfanStat.setCounts(new Random().nextInt(1000));
                aIrawselectfanStatMapper.insert(tbAIrawselectfanStat);
            }
            return new Result(true, StatusCode.OK, "原始字段选取保存成功", result.getData().toString());
        }
        return new Result(false, StatusCode.OK, "操作失败");
    }

    /**
     * 查询（原始字段选取表）
     *
     * @param selectkey :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findList(String selectkey) {
        List<TbAIrawfieldselect> list= aIrawfieldselectMapper.findList(selectkey);
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 查询（原始字段选取风机统计表）
     *
     * @param selectkey :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findListFan(String selectkey) {
        List<TbAIrawselectfanStat> list= aIrawselectfanStatMapper.findListFan(selectkey);
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
        int i = aIrawfieldselectMapper.deleteByPrimaryKey(id);
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
    public ResponseResult updateData(TbAIrawfieldselect aiData) {
        TbAIrawfieldselect tbData = aiData;
        int i = aIrawfieldselectMapper.updateByPrimaryKey(tbData);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
