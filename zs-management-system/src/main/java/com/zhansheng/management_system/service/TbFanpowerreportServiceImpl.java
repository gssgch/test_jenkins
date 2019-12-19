package com.zhansheng.management_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanpowerreport;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.request.PowerrePort;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.management_system.dao.TbFanpowerreportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/9/30 10:04
 */


@Service
@Transactional
public class TbFanpowerreportServiceImpl {

    @Autowired
    private TbFanpowerreportMapper fanpowerreportMapper;


    public Result findByYMD(PowerrePort powerrePort, PageParam pageParam) {

        if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<TbFanpowerreport> list = getFanpowerreportList(powerrePort);
        PageInfo<TbFanpowerreport> pageInfo = new PageInfo<TbFanpowerreport>(list);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", pageInfo.getList());
        map.put("size", pageInfo.getTotal());
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 查询返回数据
     *
     * @param powerrePort :
     * @return java.util.List<com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanpowerreport>
     * @Author xuzhengjie
     * @Date 2019/10/18 16:06
     */
    public List<TbFanpowerreport> getFanpowerreportList(PowerrePort powerrePort) {
        List<TbFanpowerreport> list = null;
        list = fanpowerreportMapper.findByMonthAndDay2(powerrePort);
        return list;
    }


}
