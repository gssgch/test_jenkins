package com.zhansheng.management_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.cmsowner.TbDraughtfanInterface;


import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.TbDraughtfan;
import com.zhansheng.framework.domain.UserManager.request.DraughtfanDto;
import com.zhansheng.framework.domain.UserManager.request.PageDraughtfan;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.dao.TbDraughtfanMapper;
import com.zhansheng.management_system.dao.TbLoopDraughtfanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @description
 * @date 2019/6/18
 */
@Service
@Transactional
public class TbDraughtfanServiceImpl implements TbDraughtfanInterface {

    @Autowired
    private TbLoopDraughtfanMapper loopDraughtfanMapper;

    @Autowired
    private TbDraughtfanMapper draughtfanMapper;

    /**
     * 添加风机信息
     *
     * @param draughtfanDto
     * @return
     */
    @Override
    public Result addDraughtfan(DraughtfanDto draughtfanDto) {
        if (draughtfanDto != null) {
            TbDraughtfan tbDraughtfan = draughtfanDto;
            tbDraughtfan.setFCreatetime(new Date());
            tbDraughtfan.setFUpdatetime(new Date());
            //添加风机表信息
            int addDraughtfan = draughtfanMapper.addDraughtfan(tbDraughtfan);
            //向环路风机中间表 添加数据
            int addLoopIdAndDraughtId = loopDraughtfanMapper.addLoopIdAndDraughtId(draughtfanDto.getLoopId(), tbDraughtfan.getFDraughtId());
            if (addDraughtfan == 1 && addLoopIdAndDraughtId == 1) {
                return new Result(true, StatusCode.OK, "操作成功!");
            }
        }
        return new Result(false, StatusCode.ERROR, "操作失败!");
    }

    /**
     * 删除风机
     *
     * @param draughtfanId
     * @return
     */
    @Override
    public ResponseResult deleteById(int draughtfanId) {
        int i = draughtfanMapper.deleteById(draughtfanId);
        if (i == 1) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.DELETE_ERRO);
        /*int deleteById = draughtfanMapper.deleteById(draughtfanId);
        if (deleteById == 1) {
            return new Result(true, StatusCode.OK, "操作成功!");
        }
        return new Result(false, StatusCode.ERROR, "操作失败!");*/
    }

    /**
     * 修改风机信息
     *
     * @param draughtfan
     * @return
     */
    @Override
    public Result updateDraughtfan(TbDraughtfan draughtfan) {
        if (draughtfan != null) {
            TbDraughtfan tbDraughtfan = draughtfan;
            tbDraughtfan.setFUpdatetime(new Date());
            int updateDraughtfan = draughtfanMapper.updateDraughtfan(tbDraughtfan);
            if (updateDraughtfan == 1) {
                return new Result(true, StatusCode.OK, "操作成功!");
            }
        }
        return new Result(false, StatusCode.ERROR, "操作失败!");
    }

    /**
     * 分页查询风机信息
     *
     * @param pageDraughtfan
     * @return
     */
    @Override
    public Result findDraughtfanList(PageDraughtfan pageDraughtfan) {
        if (pageDraughtfan.getPage() <= 0) {
            pageDraughtfan.setPage(1);
        }
        if (pageDraughtfan.getSize() <= 0) {
            pageDraughtfan.setSize(10);
        }
        //如果username是空就是没有模糊匹配  查询所有结果分页
        if (pageDraughtfan != null) {
            PageHelper.startPage(pageDraughtfan.getPage(), pageDraughtfan.getSize());
            List<TbDraughtfan> list = draughtfanMapper.findByloopIdAndDraughtfanList(pageDraughtfan.getLoopId());
            PageInfo<TbDraughtfan> pageInfo = new PageInfo<TbDraughtfan>(list);
            Map<Object, Object> map = new HashMap<>();
            map.put("page", pageInfo.getList());
            map.put("size", pageInfo.getTotal());
            return new Result(true, StatusCode.OK, "查询所有风机信息!", map);
        }
        return new Result(false, StatusCode.REPERROR, "查询失败!");
    }

    /**
     * 分页查询风机信息
     */
    @Override
    public Result findDraughtfanNO() {
        List<TbDraughtfan> list = draughtfanMapper.findDraughtfanNO();
        Map<Object, Object> map = new HashMap<>();
        map.put("list", list);
        return new Result(true, StatusCode.OK, "查询所有风机信息!", map);
    }

    /**
     * 上传excel 导入环路信息
     *
     * @param file
     * @return
     */
    /*public ResponseResult readExcelFile(MultipartFile file, String usename) {
        //创建处理EXCEL的类
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取上传的事件单
        List<Map<String, Object>> userList = readExcel.getExcelInfo(file);
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
        for (Map<String, Object> user : userList) {
            int ret = draughtfanMapper.insertDraughtfan(Integer.parseInt(user.get("name").toString()), usename, new Date(), new Date());
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
