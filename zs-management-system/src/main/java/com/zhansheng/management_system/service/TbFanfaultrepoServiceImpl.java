package com.zhansheng.management_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.Industrialcontrol.FanfaultrepoControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanfaultrepo;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.dao.TbFanfaultrepoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/9/27 14:14
 */


@Service
@Transactional
public class TbFanfaultrepoServiceImpl implements FanfaultrepoControllerApi {

    @Autowired
    private TbFanfaultrepoMapper fanfaultrepoMapper;


    /**
     * 添加故障知识库数据
     *
     * @param fanfaultrepo : 参数
     * @return com.zhansheng.framework.model.response.ResponseResult
     * @Author xuzhengjie
     * @Date 2019/9/27 16:36
     */
    @Override
    public ResponseResult addFanfaultrepo(TbFanfaultrepo fanfaultrepo) {
        TbFanfaultrepo tbFanfaultrepo = fanfaultrepo;
        int i = fanfaultrepoMapper.insert(tbFanfaultrepo);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public Result findList(PageParam pageParam) {
        if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<TbFanfaultrepo> list = fanfaultrepoMapper.findList();
        PageInfo<TbFanfaultrepo> pageInfo = new PageInfo<TbFanfaultrepo>(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", pageInfo.getList());
        map.put("size", pageInfo.getTotal());
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    @Override
    public ResponseResult deleteById(int Id) {
        int i = fanfaultrepoMapper.deleteByPrimaryKey(Id);
        if (i == 1) {

            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public ResponseResult updateFanfaultrepo(TbFanfaultrepo fanfaultrepo) {
        TbFanfaultrepo tbFanfaultrepo = fanfaultrepo;
        int i = fanfaultrepoMapper.updateByPrimaryKey(tbFanfaultrepo);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }


}
