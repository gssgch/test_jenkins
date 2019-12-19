package com.zhansheng.faultdetection.service;

import com.zhansheng.faultdetection.dao.TbWindmillDtoMapper;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbWindmill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/7/10
 */
@Service
public class TbWindmillDtoServiceImpl {


    @Autowired
    private TbWindmillDtoMapper windmillDtoMapper;


    /**
     * 查询风厂信息
     *
     * @return
     */
    public Result findList() {
        List<TbWindmill> list = windmillDtoMapper.findList();
        return new Result(true, StatusCode.OK, "操作成功!", list);
    }


}
