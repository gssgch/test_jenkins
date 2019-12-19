package com.zhansheng.management_system.AIservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.aiManager.AialgorithmControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.domain.aiManager.TbAIalgorithm;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.AIdao.TbAIalgorithmMapper;
import com.zhansheng.management_system.utils.AIConstant;
import com.zhansheng.management_system.utils.AIKeyUtils;
import com.zhansheng.management_system.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhansheng
 */
@Service
public class AialgorithmServiceImpl implements AialgorithmControllerApi {

    @Autowired
    private TbAIalgorithmMapper tbAIalgorithmMapper;

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
    public ResponseResult addData(TbAIalgorithm aiData) throws IOException {
        TbAIalgorithm tbAiData = aiData;
        Result result = aiKeysnumService.getNumByKey(tbAiData.getTopic() + AIConstant.AI_SF);
        tbAiData.setAlgokey(result.getData().toString());
        tbAiData.setCreatetime(new Date());
        //py文件路径加文件名
        String path = AIConstant.PATH_PREFIX + tbAiData.getTopic() +
                AIConstant.PATH_ALGOSCRIPT + tbAiData.getAlgokey() + AIConstant.EXTENSION;
        tbAiData.setShellpath(path);
        int i = tbAIalgorithmMapper.insert(tbAiData);
        if (i == 1) {

            //生成py文件
            fileUtils.write(path, tbAiData.getCoding());
            //更新index表（更新后的index值）
            aiKeyUtils.updateIndex(tbAiData.getTopic(), AIConstant.AI_SF, result.getData().toString());
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 查询（算法表）分页
     *
     * @param algokey :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findList(PageParam pageParam, String algokey) {
        if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<TbAIalgorithm> list= tbAIalgorithmMapper.findList(algokey,null);
        for (TbAIalgorithm ai: list) {
            String coding = fileUtils.readFileByLines("/home/shdq/001/algoscript/101_sf_00000001.py");
            ai.setCoding(coding);
        }
        PageInfo<TbAIalgorithm> pageInfo = new PageInfo<TbAIalgorithm>(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", pageInfo.getList());
        map.put("size", pageInfo.getTotal());
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 查询（算法表）不分页
     *
     * @param topic :
     * @return com.zhansheng.framework.domain.Results.Result
     */
    @Override
    public Result findListBykey(String topic) {
        List<TbAIalgorithm> list= tbAIalgorithmMapper.findList(null, topic);
        if(!list.isEmpty()) {
            for (TbAIalgorithm ai:list) {
               String coding = fileUtils.readFileByLines("/home/shdq/001/algoscript/101_sf_00000001.py");
                ai.setCoding(coding);
            }
        }
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
        int i = tbAIalgorithmMapper.deleteByPrimaryKey(id);
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
    public ResponseResult updateData(TbAIalgorithm aiData) {
        TbAIalgorithm tbData = aiData;
        int i = tbAIalgorithmMapper.updateByPrimaryKey(tbData);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }


}
