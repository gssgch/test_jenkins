package com.zhansheng.management_system.service;

import com.zhansheng.api.Industrialcontrol.FanFaultRepairControllerApi;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanFaultRepair;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFault;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.dao.TbFanFaultMapper;
import com.zhansheng.management_system.dao.TbFanFaultRepairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author xufu
 * @description 添加检修（故障检修表处理）
 * @date 2019/9/30 13:44
 */
@Service
@Transactional
public class TbFanFaultRepairServiceImpl implements FanFaultRepairControllerApi {

    @Autowired
    private TbFanFaultRepairMapper fanFaultRepairMapper;

    @Autowired
    private TbFanFaultMapper fanFaultMapper;

    /**
     * 向告警中心添加数据
     *
     * @param fanFaultRepair :
     * @return com.zhansheng.framework.model.response.ResponseResult
     * @Author xufu
     * @Date 2019/9/30 14:26
     */
    @Override
    public ResponseResult addFanFaultRepair(TbFanFaultRepair fanFaultRepair) {
        TbFanFaultRepair fanFaultRepairInfo = fanFaultRepair;
        TbFault tbFault = new TbFault();
        tbFault.setId(fanFaultRepairInfo.getFkFanfaultId());
        tbFault.setStatusCode(fanFaultRepairInfo.getStatusCode());
        fanFaultRepairInfo.setRepairDate(new Date());
        fanFaultRepairInfo.setStatus(1);
        int i = fanFaultRepairMapper.insert(fanFaultRepairInfo);
        //更新故障状态
        int j = fanFaultMapper.updateByPrimaryKey(tbFault);
        if (i == 1 && j == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
