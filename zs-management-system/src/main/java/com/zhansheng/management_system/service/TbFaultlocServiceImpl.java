package com.zhansheng.management_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.Industrialcontrol.FaultlocControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultLoc;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.framework.model.response.ResponseResult;
import com.zhansheng.management_system.dao.TbFaultLocMapper;
import com.zhansheng.management_system.utils.RedisService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/9/29 17:50
 */
@Service
@Transactional
public class TbFaultlocServiceImpl implements FaultlocControllerApi {


    @Autowired
    private TbFaultLocMapper faultLocMapper;

    @Autowired
    private BaseServiceImpl baseService;

    @Value("${files.rootPath}")
    private String rootPath;

    /*@Autowired
    private RedisTemplate<Object, Object> redisTemplate;*/
    @Autowired
    RedisService redisService;

    //增删改同步redis数据
    private void getRedis() {
        //查询redis 是否存在
        List<TbFaultLoc> list = (List<TbFaultLoc>) redisService.getListByKey("fault_loc", 0, -1);
        if (list == null || list.size() <= 0) {
            //如果不存在 如果不存在重新查询 存入redis
            list = baseService.getLocList();
        } else {
            //如果redis存在 删除 并重新写入 redis
            redisService.delObj("fault_loc");
            list = faultLocMapper.findList();
            for (TbFaultLoc loc : list) {
                redisService.addList("fault_loc", loc);
            }
        }
    }


    public ResponseResult addFaultLoc(HttpServletRequest request,
                                      HttpServletResponse rssponse,
                                      TbFaultLoc faultLoc,
                                      MultipartFile file) {
        if (file == null) {
            TbFaultLoc tbFaultLoc = faultLoc;
            int i = faultLocMapper.insert(tbFaultLoc);
            if (i == 1) {
                getRedis();
                return new ResponseResult(CommonCode.SUCCESS);
            }
            return new ResponseResult(CommonCode.FAIL);
        } else {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;

            try {
                //获取图片名称
                String fileOrigName = file.getOriginalFilename();
                if (!fileOrigName.contains(".")) {
                    throw new IllegalArgumentException("缺少后缀名");
                }
                TbFaultLoc tbFaultLoc = faultLoc;
                //获取图片名称
                String str = fileOrigName.substring(fileOrigName.lastIndexOf("."));
                //通过时间戳生成随机数
                String s = toHex(new Date().getTime());
                //字符串拼接  图片名称
                String fileName = s + str;

                // 文件保存地址
                String fullPath = basePath + "/images/" + fileName;
                tbFaultLoc.setPicture(fullPath);
                int i = faultLocMapper.insert(tbFaultLoc);
                // 这里使用Apache的FileUtils方法来进行保存
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(rootPath,
                        fileName));
                if (i == 1) {
                    getRedis();
                    return new ResponseResult(CommonCode.SUCCESS);
                }
                return new ResponseResult(CommonCode.FAIL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ResponseResult(CommonCode.FAIL);
    }


    /**
     * 获取8位不重复随机码（取当前时间戳转化为十六进制）
     *
     * @param time
     * @return
     * @author ShelWee
     */
    public static String toHex(long time) {
        return Integer.toHexString((int) time);
    }


    /**
     * 分页查询
     *
     * @param pageParam :
     * @return com.zhansheng.framework.domain.Results.Result
     * @Author xuzhengjie
     * @Date 2019/10/9 13:46
     */
    public Result findList(PageParam pageParam) {
        if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
        //先查询redis中有没有数据
        Long i = redisService.getFindSize("fault_loc");
        List<TbFaultLoc> list = null;
        if (i <= 0) {
            synchronized (this) {
                list = (List<TbFaultLoc>) redisService.getListByKey("fault_loc",
                        pageParam.getSize() * (pageParam.getPage() - 1),
                        (pageParam.getPage() * pageParam.getSize()) - 1);
                if (list == null || list.size() <= 0) {
                    List<TbFaultLoc> list2 = faultLocMapper.findList();
                    PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
                    List<TbFaultLoc> list1 = faultLocMapper.findList();
                    PageInfo<TbFaultLoc> pageInfo = new PageInfo<TbFaultLoc>(list1);
                    for (TbFaultLoc loc : list2) {
                        redisService.addList("fault_loc", loc);
                    }
                    Map<Object, Object> map = new HashMap<>();
                    map.put("page", pageInfo.getList());
                    map.put("size", pageInfo.getTotal());
                    return new Result(true, StatusCode.OK, "查询成功 !", map);
                } else {
                    //查询redis 数据库
                    Map<Object, Object> map = getRedisList(pageParam);
                    return new Result(true, StatusCode.OK, "查询成功 !", map);
                }
            }
        } else {
            //查询redis 数据库
            Map<Object, Object> map = getRedisList(pageParam);
            return new Result(true, StatusCode.OK, "查询成功 !", map);
        }
    }

    private Map<Object, Object> getRedisList(PageParam pageParam) {
        Long i;
        List<TbFaultLoc> list;
        i = redisService.getFindSize("fault_loc");
        list = (List<TbFaultLoc>) redisService.getListByKey("fault_loc",
                pageParam.getSize() * (pageParam.getPage() - 1),
                (pageParam.getPage() * pageParam.getSize()) - 1);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", list);
        map.put("size", i);
        return map;
    }


    public ResponseResult deleteById(int id) {
        int i = faultLocMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            getRedis();
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.DELETE_ERRO);
    }

    @Override
    public ResponseResult updateFaultLoc(HttpServletRequest request, HttpServletResponse response, TbFaultLoc faultLoc, MultipartFile file) {

        if (file == null) {
            TbFaultLoc tbFaultLoc = faultLoc;
            int i = faultLocMapper.updateNotFile(tbFaultLoc);
            if (i == 1) {
                getRedis();
                return new ResponseResult(CommonCode.SUCCESS);
            }
            return new ResponseResult(CommonCode.FAIL);

        } else {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
            try {
                //获取图片名称
                String fileOrigName = file.getOriginalFilename();
                if (!fileOrigName.contains(".")) {
                    throw new IllegalArgumentException("缺少后缀名");
                }
                TbFaultLoc tbFaultLoc = faultLoc;
                //获取图片名称
                String str = fileOrigName.substring(fileOrigName.lastIndexOf("."));
                //通过时间戳生成随机数
                String s = toHex(new Date().getTime());
                //字符串拼接  图片名称
                String fileName = s + str;

                // 文件保存地址
                String fullPath = basePath + "/images/" + fileName;
                tbFaultLoc.setPicture(fullPath);
                int i = faultLocMapper.updateByPrimaryKey(tbFaultLoc);
                // 这里使用Apache的FileUtils方法来进行保存
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(rootPath,
                        fileName));
                if (i == 1) {
                    getRedis();
                    return new ResponseResult(CommonCode.SUCCESS);
                }
                return new ResponseResult(CommonCode.FAIL);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseResult(CommonCode.FAIL);
    }


}