package com.zhansheng.management_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.cmsowner.TbLoopInterface;

import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Loop;
import com.zhansheng.framework.domain.UserManager.TbDraughtfanLoop;
import com.zhansheng.framework.domain.UserManager.request.LoopDto;
import com.zhansheng.framework.domain.UserManager.request.PageLoop;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;

import com.zhansheng.management_system.dao.TbDraughtfanMapper;
import com.zhansheng.management_system.dao.TbLoopDraughtfanMapper;
import com.zhansheng.management_system.dao.TbLoopMapper;
import com.zhansheng.management_system.dao.TbWindmillLoopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @description
 * @date 2019/6/14
 */


@Service
@Transactional
public class TbLoopServiceImpl implements TbLoopInterface {

    @Autowired
    private TbLoopMapper loopMapper;

    @Autowired
    private TbWindmillLoopMapper windmillLoopMapper;

    @Autowired
    private TbDraughtfanMapper draughtfanMapper;

    @Autowired
    private TbLoopDraughtfanMapper loopDraughtfanMapper;

    /**
     * 添加环路信息
     *
     * @param
     * @return
     */
    @Override
    public Result addLoop(LoopDto loopDto) {
        if (loopDto == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Loop loop = loopDto;
        int i = loopMapper.addLoop(loop);

        int addWindmillAndLoop = windmillLoopMapper.addWindmillAndLoop(loopDto.getWindmillId(), loop.getFLoopId());
        if (i == 1 && addWindmillAndLoop == 1) {
            return new Result(true, StatusCode.OK, "操作成功!");
        }
        return null;
    }

    /**
     * 删除环路信息 并输出所属风机
     *
     * @param loopId
     * @return
     */
    @Override
    public Result deleteById(int loopId) {
        //通过中间表获取风机Id
        List<TbDraughtfanLoop> draughtfanIds = loopDraughtfanMapper.findByLoopIdAndDraughtfanId(loopId);

        //System.out.println("id :" + draughtfanIds);
        //删除环路信息
        loopMapper.deleteById(loopId);
        //删除风厂环路信息
        deleteWindmillLoop(loopId);
        //删除环路 风机信息
        deleteloopDraughtfan(loopId);
        //删除风机信息
        deleteDraughtfan(draughtfanIds);

        return new Result(true, StatusCode.OK, "操作成功!");
    }

    //删除风厂环路信息
    private int deleteWindmillLoop(int loopId) {
        return windmillLoopMapper.deleteWindmillLoop(loopId);
    }

    //删除环路 风机信息
    private int deleteloopDraughtfan(int loopId) {
        return loopDraughtfanMapper.deleteByloopIdAndDraughtfan(loopId);
    }

    //删除风机信息
    private void deleteDraughtfan(List<TbDraughtfanLoop> draughtfanIds) {
        for (TbDraughtfanLoop draughtfan : draughtfanIds) {
            Integer fDraughtId = draughtfan.getFDraughtId();
            draughtfanMapper.deleteById(fDraughtId);
        }


        /*for (TbDraughtfan draughtfanId : draughtfanIds) {
            Integer draughtId = draughtfanId.getFDraughtId();
            draughtfanMapper.deleteById(draughtId);
        }*/
    }

    @Override
    public Result updateLoop(Loop loop) {
        if (loop != null) {
            Loop tbLoop = loop;
            int updateLoop = loopMapper.updateLoop(tbLoop);
            if (updateLoop == 1) {
                return new Result(true, StatusCode.OK, "操作成功!");
            }
        }
        return null;
    }

    @Override
    public Result findWindmillIdByLoopList(PageLoop pageLoop) {

        if (pageLoop.getPage() <= 0) {
            pageLoop.setPage(1);
        }
        if (pageLoop.getSize() <= 0) {
            pageLoop.setSize(10);
        }
        //如果username是空就是没有模糊匹配  查询所有结果分页
        if (pageLoop != null) {
            PageHelper.startPage(pageLoop.getPage(), pageLoop.getSize());
            List<Loop> list = loopMapper.findByloopIdAndDraughtfanList(pageLoop.getWindmillId());
            PageInfo<Loop> pageInfo = new PageInfo<Loop>(list);
            Map<Object, Object> map = new HashMap<>();
            map.put("page", pageInfo.getList());
            map.put("size", pageInfo.getTotal());
            return new Result(true, StatusCode.OK, "查询所有风机信息!", map);
        }
        return new Result(false, StatusCode.REPERROR, "查询失败!");
    }


    /**
     * 上传excel 导入环路信息
     * @param file
     * @return
     */
    /*public ResponseResult readExcelFile(MultipartFile file) {
        //创建处理EXCEL的类
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取上传的事件单
        List<Map<String, Object>> userList = readExcel.getExcelInfo(file);
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
        for (Map<String, Object> user : userList) {
            int ret = loopMapper.insertLoop(user.get("name").toString());
            if (ret == 0) {
                //result = "插入数据库失败";
                ExceptionCast.cast(CommonCode.SERVER_SQL);
            }
        }
        if (userList != null && !userList.isEmpty()) {
            return new ResponseResult(CommonCode.SUCCESS);
        } else {
            ExceptionCast.cast(CommonCode.FAIL);
        }
        return new ResponseResult(CommonCode.FAIL);
    }*/


}
