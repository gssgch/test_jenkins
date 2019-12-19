package com.zhansheng.management_system.service;

import com.zhansheng.api.cmsowner.TbWindmillInterface;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Loop;
import com.zhansheng.framework.domain.UserManager.TbDraughtfan;
import com.zhansheng.framework.domain.UserManager.TbWindmill;
import com.zhansheng.framework.domain.UserManager.ext.ReadExcel;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @description
 * @date 2019/6/14
 */
@Service
@Transactional
public class TbWindmillServiceImpl implements TbWindmillInterface {

    @Autowired
    private TbWindmillMapper windmillMapper;

    @Autowired
    private TbWindmillLoopMapper windmillLoopMapper;

    @Autowired
    private TbLoopMapper loopMapper;

    @Autowired
    private TbDraughtfanMapper draughtfanMapper;

    @Autowired
    private TbLoopDraughtfanMapper loopDraughtfanMapper;

    /**
     * 添加风厂信息
     *
     * @param windmill
     * @return
     */
    @Override
    public Result addWindmill(TbWindmill windmill) {
        if (windmill == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        List<TbWindmill> list = windmillMapper.findList();
        if (list.size() > 0) {
            ExceptionCast.cast(CommonCode.SERVER_WINDMILL);
        }
        TbWindmill tbwindmill1 = windmill;
        tbwindmill1.setFCreatetime(new Date());
        tbwindmill1.setFUpdatetime(new Date());
        int addWindmill = windmillMapper.addWindmill(tbwindmill1);
        if (addWindmill == 1) {
            return new Result(true, StatusCode.OK, "操作成功!");
            //ExceptionCast.cast(CommonCode.SUCCESS);
        }
        return new Result(false, StatusCode.ERROR, "操作失败!");
    }

    /**
     * 删除风厂信息 环路 中间表
     *
     * @param windmillId
     * @return
     */
    @Override
    public Result deleteById(int windmillId) {
        //删除风厂信息
        windmillMapper.deleteById(windmillId);
        //删除风厂环路中间表信息
        deleteWindmillLoopId();
        //删除环路表信息
        deleteLoop();
        //删除风机信息
        deleteDraughtfan();
        //删除环路风机 中间表信息
        deleteLoopDraughtfan();
        return new Result(true, StatusCode.OK, "操作成功!");
    }

    /**
     * 删除风厂环路信息
     *
     * @param
     * @return
     */
    private int deleteWindmillLoopId() {
        return windmillLoopMapper.deleteByWindmillIdAndLoopId();
    }

    /**
     * 删除环路信息
     *
     * @param
     * @return
     */
    private int deleteLoop() {
        return loopMapper.deleteLoop();
    }

    /**
     * 删除风机信息
     *
     * @param
     * @return
     */
    private int deleteDraughtfan() {
        return draughtfanMapper.deleteDraughtfan();
    }

    /**
     * 删除环路 风机信息
     *
     * @param
     * @return
     */
    private int deleteLoopDraughtfan() {
        return loopDraughtfanMapper.deleteLoopAndDraughtfan();
    }

    /**
     * 修改风厂信息
     *
     * @param windmill
     * @return
     */
    @Override
    public Result updateByWindmill(TbWindmill windmill) {
        if (windmill == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        TbWindmill tbWindmill = windmill;
        tbWindmill.setFUpdatetime(new Date());
        int updateByWindmill = windmillMapper.updateByWindmill(tbWindmill);
        if (updateByWindmill == 1) {
            return new Result(true, StatusCode.OK, "操作成功!");
        }
        return new Result(false, StatusCode.ERROR, "操作失败!");
    }

    /**
     * 查询风厂信息
     *
     * @return
     */
    @Override
    public Result findList() {
        List<TbWindmill> list = windmillMapper.findList();
        return new Result(true, StatusCode.OK, "操作成功!", list);
    }


    /**
     * execl表格导入风厂 环路 风机
     *
     * @param file
     * @param usename
     * @return
     */
    public ResponseResult readExcelFile(MultipartFile file, String usename) {
        //创建处理EXCEL的类
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取上传的事件单
        List<Map<String, Object>> userList = readExcel.getExcelInfo(file);
        //1 像风厂表添加数据
        //2 像环路表添加数据
        //3 像风机表添加添加编号
        //1.1 查询风厂是否在数据库存在
        List<TbWindmill> windmillList = windmillMapper.findList();
        //2.1 查询环路信息
        //3.1 查询风机信息
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
        Integer windmillid = null;
        Integer draughtfanId = null;
        String loopName = "";
        Integer loopId = null;
        Integer loopId1 = null;
        Integer loopId2 = null;
        for (Map<String, Object> user : userList) {
            //1.1 后台管理系统如果没有风厂 就添加风厂
            if (windmillList.size() == 0) {
                List<TbWindmill> list = windmillMapper.findList();
                TbWindmill windmill = new TbWindmill();
                if (list.size() == 0) {
                    windmill.setFWindmillName(user.get("windmill").toString());
                    windmill.setFCreateuser(usename);
                    windmill.setFUpdatetime(new Date());
                    windmill.setFCreatetime(new Date());
                    windmillMapper.addWindmill(windmill);
                }
                if (windmill.getFWindmillId() != null) {
                    windmillid = windmill.getFWindmillId();
                }
                //2.1 查询所有环路
                Loop loop = new Loop();
                String loopnmae = user.get("loopnmae").toString();
                loop.setFLoopName(loopnmae);
                //2.12 判断是否相同数据的环路名称
                if (loop != null) {
                    if (!loopName.equals(loop.getFLoopName())) {
                        loopName = loop.getFLoopName();
                        loopMapper.addLoop(loop);
                        //像风厂环路中间表添加数据
                        if (loop.getFLoopId() != null) {
                            loopId = loop.getFLoopId();
                        }
                        loopId1 = loop.getFLoopId();
                        windmillLoopMapper.addWindmillAndLoop(windmillid, loopId);
                        //添加风机
                        TbDraughtfan draughtfan = new TbDraughtfan();
                        draughtfan.setFDraughtNumber(Integer.parseInt(user.get("number").toString()));
                        draughtfan.setFUpdateuser(usename);
                        draughtfan.setFUpdatetime(new Date());
                        draughtfan.setFCreatetime(new Date());
                        draughtfanMapper.addDraughtfan(draughtfan);
                        if (draughtfan.getFDraughtId() != null) {
                            draughtfanId = draughtfan.getFDraughtId();
                        }
                        //像环路中间表添加数据
                        loopDraughtfanMapper.addLoopIdAndDraughtId(loopId, draughtfanId);
                    } else {
                        //添加风机
                        TbDraughtfan draughtfan = new TbDraughtfan();
                        draughtfan.setFDraughtNumber(Integer.parseInt(user.get("number").toString()));
                        draughtfan.setFUpdateuser(usename);
                        draughtfan.setFUpdatetime(new Date());
                        draughtfan.setFCreatetime(new Date());
                        draughtfanMapper.addDraughtfan(draughtfan);
                        if (draughtfan.getFDraughtId() != null) {
                            draughtfanId = draughtfan.getFDraughtId();
                        }
                        //像环路中间表添加数据
                        loopDraughtfanMapper.addLoopIdAndDraughtId(loopId1, draughtfanId);
                    }
                }
            }
            //2.1 如果风厂信息在数据库存在
            if (windmillList.size() > 0) {
                List<TbWindmill> list = windmillMapper.findList();
                //拿到风厂id查询环路id
                for (TbWindmill windmill : list) {
                    Integer windmillId = windmill.getFWindmillId();
                    //1.1 如果环路信息在风厂存在
                    String loopnmae = user.get("loopnmae").toString();
                    Loop loop = loopMapper.findByLoopName(loopnmae);
                    if (loop != null) {
                        if (loop.getFLoopId() != null) {
                            loopId2 = loop.getFLoopId();
                        }
                        //1.2 就添加风机信息 和 风机环路中间表信息
                        //添加风机
                        TbDraughtfan draughtfan = new TbDraughtfan();
                        draughtfan.setFDraughtNumber(Integer.parseInt(user.get("number").toString()));
                        draughtfan.setFUpdateuser(usename);
                        draughtfan.setFUpdatetime(new Date());
                        draughtfan.setFCreatetime(new Date());
                        draughtfanMapper.addDraughtfan(draughtfan);
                        if (draughtfan.getFDraughtId() != null) {
                            draughtfanId = draughtfan.getFDraughtId();
                        }
                        //像环路中间表添加数据
                        loopDraughtfanMapper.addLoopIdAndDraughtId(loopId2, draughtfanId);
                    } else {
                        //2.1 如果环路信息不存在
                        //2.2 就添加环路表数据 和 环路中间表
                        Loop loop1 = new Loop();
                        String loopnmae1 = user.get("loopnmae").toString();
                        loop1.setFLoopName(loopnmae1);
                        loopMapper.addLoop(loop1);
                        if (loop1.getFLoopId() != null) {
                            loopId1 = loop1.getFLoopId();
                        }
                        windmillLoopMapper.addWindmillAndLoop(windmillId, loopId1);
                        //2.3 就添加风机信息 和 风机环路中间表信息
                        TbDraughtfan draughtfan = new TbDraughtfan();
                        draughtfan.setFDraughtNumber(Integer.parseInt(user.get("number").toString()));
                        draughtfan.setFUpdateuser(usename);
                        draughtfan.setFUpdatetime(new Date());
                        draughtfan.setFCreatetime(new Date());
                        draughtfanMapper.addDraughtfan(draughtfan);
                        if (draughtfan.getFDraughtId() != null) {
                            draughtfanId = draughtfan.getFDraughtId();
                        }
                        //像环路中间表添加数据
                        loopDraughtfanMapper.addLoopIdAndDraughtId(loopId1, draughtfanId);

                    }
                }
            }
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }


}
