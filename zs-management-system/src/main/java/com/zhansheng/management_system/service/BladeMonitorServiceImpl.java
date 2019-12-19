package com.zhansheng.management_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhansheng.api.Industrialcontrol.BladeMonitorControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFault;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbVoiceVideo;
import com.zhansheng.framework.domain.UserManager.TbDraughtfan;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.management_system.dao.BladeMonitorMapper;
import com.zhansheng.management_system.dao.TbVoiceVideoMapper;
import com.zhansheng.management_system.dao.TbWindmillMapper;
import com.zhansheng.management_system.utils.MP3ToWavUtil;
import com.zhansheng.management_system.utils.WavFileReadUtil;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xufu
 * @description 叶片监测
 * @date 2019/9/30 13:44
 */
@Service
@Transactional
public class BladeMonitorServiceImpl implements BladeMonitorControllerApi {

    private Logger logger = LoggerFactory.getLogger(BladeMonitorServiceImpl.class);

    @Autowired
    private BladeMonitorMapper bladeMonitorMapper;

    @Autowired
    private TbWindmillMapper windmillMapper;

    @Autowired
    private TbVoiceVideoMapper voiceVideoMapper;

    private MP3ToWavUtil mp3ToWavUtil;

    private WavFileReadUtil wavFileReadUtil;


    @Value("${hdfs.path}")
    private String path;
    //下载之后本地文件路径（如果本地文件目录不存在，则会自动创建）
    @Value("${files.hdfsLocalPath}")
    private String dstFile;
    @Value("${hdfs.username}")
    private String username;
    //hdfs相对路径
    @Value("${files.hdfsVideoPath}")
    private String hdfsVideoPath;
    @Value("${files.hdfsVoicePath}")
    private String hdfsVoicePath;

    private static String hdfsPath;
    private static String hdfsName;

    /**
     * 分页查询
     *
     * @param pageParam :分页
     * @param healthyState : 健康状态
     * @return com.zhansheng.framework.domain.Results.Result
     * @Author xufu
     * @Date 2019/9/30 14:33
     */
    @Override
    public Result findList(PageParam pageParam, Integer healthyState, Integer faultFrom) {
        //查询风场名
        String windmillName = windmillMapper.findList().get(0).getFWindmillName();

        Integer count = bladeMonitorMapper.findFanCount();

        //查询各状态总数
        Map<String, Object> stateCount = bladeMonitorMapper.findCount(faultFrom, count);
        if (pageParam.getPage() <= 0) {
            pageParam.setPage(1);
        }
        if (pageParam.getSize() <= 0) {
            pageParam.setSize(10);
        }
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<TbDraughtfan> listfan = bladeMonitorMapper.findFan();
        for (TbDraughtfan f:listfan) {
            List<TbDraughtfan> listresult = bladeMonitorMapper.findList(faultFrom,
                    healthyState, count, f.getFDraughtNumber());
            f.setMapFan(listresult);
        }
        PageInfo<TbDraughtfan> pageInfo = new PageInfo<TbDraughtfan>(listfan);
        Map<Object, Object> map = new HashMap<>();
        map.put("page", pageInfo.getList());
        map.put("size", pageInfo.getTotal());
        map.put("windmillName", windmillName);
        map.put("stateCount", stateCount);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 获取HDFS配置信息
     * @return
     */
    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", hdfsPath);
        return configuration;
    }

    /**
     * 获取HDFS文件系统对象
     * @return
     * @throws Exception
     */
    public static FileSystem getFileSystem() throws Exception  {
        // 客户端去操作hdfs时是有一个用户身份的，默认情况下hdfs客户端api会从jvm中获取一个参数作为自己的用户身份
        // DHADOOP_USER_NAME=hadoop
        // 也可以在构造客户端fs对象时，通过参数传递进去
        FileSystem fileSystem = FileSystem.get(new URI(hdfsPath), getConfiguration(), "hdfs");
        return fileSystem;
    }

    /**
     * 判断HDFS文件是否存在
     * @param path
     * @return
     * @throws Exception
     */
    public static boolean existFile(String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        FileSystem fs = getFileSystem();
        Path srcPath = new Path(path);
        boolean isExists = fs.exists(srcPath);
        return isExists;
    }

    /**
     * 读取HDFS目录信息
     * @param path
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> readPathInfo(String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }
        FileSystem fs = getFileSystem();
        // 目标路径
        Path newPath = new Path(path);
        FileStatus[] statusList = fs.listStatus(newPath);
        List<Map<String, Object>> list = new ArrayList<>();
        if (null != statusList && statusList.length > 0) {
            for (FileStatus fileStatus : statusList) {
                Map<String, Object> map = new HashMap<>();
                map.put("filePath", fileStatus.getPath());
                map.put("fileStatus", fileStatus.toString());
                list.add(map);
            }
            return list;
        } else {
            return null;
        }
    }

    /**
     * 读取HDFS文件列表
     * @param path
     * @return
     * @throws Exception
     */
    public static List<Map<String, String>> listFile(String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }

        FileSystem fs = getFileSystem();
        // 目标路径
        Path srcPath = new Path(path);
        // 递归找到所有文件
        RemoteIterator<LocatedFileStatus> filesList = fs.listFiles(srcPath, true);
        List<Map<String, String>> returnList = new ArrayList<>();
        while (filesList.hasNext()) {
            LocatedFileStatus next = filesList.next();
            String fileName = next.getPath().getName();
            Path filePath = next.getPath();
            Map<String, String> map = new HashMap<>();
            map.put("fileName", fileName);
            map.put("filePath", filePath.toString());
            returnList.add(map);
        }
        fs.close();
        return returnList;
    }

    /**
     * 分页查询
     *
     * @param faultFrom :数据来源
     * @param fDraughtNumber :风机编号
     * @return com.zhansheng.framework.domain.Results.Result
     * @Author xufu
     * @Date 2019/9/30 14:33
     */
    @Override
    public Result remoteInspection(Integer faultFrom, Integer fDraughtNumber) throws Exception{

        //从HDFS中获取音频和视频文件名和链接
        List<TbVoiceVideo> voiceVideo = voiceVideoMapper.findByFanNum(fDraughtNumber);

        if(!voiceVideo.isEmpty()) {
            for (TbVoiceVideo v: voiceVideo) {
                if (v.getVoiceUrl() != null) {
                    v.setVoiceUrl(hdfsPath + v.getVoiceUrl());
                }
                if (v.getVideoUrl() != null) {
                    v.setVideoUrl(hdfsPath + v.getVideoUrl());
                }
                if (v.getVibration() != null) {
                    v.setVibration(hdfsPath + v.getVibration());
                }
            }
        }

        //获取故障历史表格数据
        List<TbFault> faultList =  bladeMonitorMapper.findFaultList(faultFrom, fDraughtNumber);

        Map<Object, Object> map = new HashMap<>();
        map.put("faultList", faultList);
        map.put("voiceVideo", voiceVideo);
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 在线播放视频文件
     *
     * @param req :
     * @param resp :
     * @param filePath :文件路径
     * @return com.zhansheng.framework.domain.Results.Result
     * @Author xufu
     * @Date 2019/9/30 14:33
     */
    @Override
    public void playVideo(HttpServletRequest req, HttpServletResponse resp, String filePath)
            throws ClassNotFoundException, IOException {

        if(filePath==null) {
            return;
        }
        String filetype = null;
        filetype = filePath.substring(filePath.lastIndexOf(".")+1);

        filePath = generateHdfsPath(filePath);
        String filename=null;
        Configuration config=new Configuration();
        FileSystem fs = null;
        FSDataInputStream in=null;
        try {
            fs = FileSystem.get(URI.create(filePath),config);
            in=fs.open(new Path(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final long fileLen = fs.getFileStatus(new Path(filePath)).getLen();
        String range=req.getHeader("Range");
        resp.setHeader("Content-type","video/mp4");
        OutputStream out=resp.getOutputStream();

        if(range==null)
        {
            filename=filePath.substring(filePath.lastIndexOf("/")+1);
            resp.setHeader("Content-Disposition", "attachment; filename="+filename);
            resp.setContentType("application/octet-stream");
            resp.addHeader("Access-Control-Allow-Origin", "*");
            resp.setContentLength((int)fileLen);
            IOUtils.copyBytes(in, out, fileLen, false);
        }
        else
        {
            long start=Integer.valueOf(range.substring(range.indexOf("=")+1, range.indexOf("-")));
            long count=fileLen-start;
            long end;
            if(range.endsWith("-"))
                end=fileLen-1;
            else
                end=Integer.valueOf(range.substring(range.indexOf("-")+1));
            String ContentRange="bytes "+String.valueOf(start)+"-"+end+"/"+String.valueOf(fileLen);
            resp.setStatus(206);
            if (filetype == "mp3") {
                resp.setContentType("audio/mp3");
            } else if (filetype == "mp4") {
                resp.setContentType("video/mpeg4");
            }
            resp.setHeader("Content-Range",ContentRange);
            in.seek(start);
            try{
                IOUtils.copyBytes(in, out, count, false);
            }
            catch(Exception e)
            {
                throw e;
            }
        }
        in.close();
        in = null;
        out.close();
        out = null;
    }

    /**
     * 在线播放音频文件
     *
     * @param req :
     * @param resp :
     * @param filePath :文件路径
     * @return com.zhansheng.framework.domain.Results.Result
     * @Author xufu
     * @Date 2019/9/30 14:33
     */
    @Override
    public Result playVoice(HttpServletRequest req, HttpServletResponse resp, String filePath) throws ClassNotFoundException, Exception {
        Map<Object, Object> map = new HashMap<>();
        LinkedList<Short> data = null;
        LinkedList<Float> voiceData = new LinkedList<Float>();
        if(filePath==null) {
            return new Result(true, StatusCode.OK, "文件名为空！");
        }

        String filetype = filePath.substring(filePath.lastIndexOf(".")+1);

        //如果是音频
        if ("mp3".equals(filetype)) {
            //从HFDS上下载音频为mp3格式的文件
            String localDstPath = downloadFileFromHdfs(filePath);
            //讲音频从MP3格式转换为WAV格式，并保存
            //本地WAV文件地址
            String wavPath = null;
            String filename=filePath.substring(filePath.lastIndexOf("/")+1);
            boolean flg = false;
            if (localDstPath != null) {
                wavPath = (localDstPath + "/"+ filename).replace("mp3", "wav");
                flg = mp3ToWavUtil.byteToWav(mp3ToWavUtil.getBytes(localDstPath+ "/"+ filename), wavPath);
            }
            if (flg){
                //获取WAV格式音频的频率
                data = WavFileReadUtil.readWav(wavPath);
            }
            if (data != null && data.size() != 0) {
                //遍历data,将数据整体缩小500倍，提供给前端更好的显示
                for(short a:data){
                    float b = ((float)Math.round((float)a/130*10000)/10000);
                    voiceData.add(b);
                }
                map.put("data", voiceData);
            } else {
                return new Result(true, StatusCode.OK, "音频文件损坏！");
            }

        } else {
            return new Result(true, StatusCode.OK, "音频格式不对！");
        }
        return new Result(true, StatusCode.OK, "查询成功 !", map);

    }

    /**
     * 在线播放振动文件
     *
     * @param req :
     * @param resp :
     * @param filePath :文件路径
     * @return com.zhansheng.framework.domain.Results.Result
     * @Author xufu
     * @Date 2019/9/30 14:33
     */
    @Override
    public Result playVibration(HttpServletRequest req, HttpServletResponse resp, String filePath) throws ClassNotFoundException, Exception {
        Map<Object, Object> map = new HashMap<>();
        //HDFS文件路径
        Path hdfsPath = new Path(generateHdfsPath(filePath));

        FileSystem fileSystem = null;
        FSDataInputStream inputStream = null;
        try {
            fileSystem = getFileSystem();
            inputStream = fileSystem.open(hdfsPath);

            String vibrationData =  mp3ToWavUtil.toString(inputStream);
            map.put("vibrationData" , vibrationData);
        } catch (IOException e) {
            logger.error(MessageFormat.format("打开HDFS上面的文件失败，path:{0}",filePath),e);
            return new Result(true, StatusCode.OK, "获取振动数据失败 !");
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    return new Result(true, StatusCode.OK, "获取振动数据失败 !");
                }
            }
        }
        return new Result(true, StatusCode.OK, "查询成功 !", map);
    }

    /**
     * 从HDFS下载文件至本地
     * @author xufu
     * @param srcFile HDFS的相对目录路径，比如：/testDir/a.txt
     */
    public String downloadFileFromHdfs(String srcFile){
        //HDFS文件路径
        Path hdfsSrcPath = new Path(generateHdfsPath(srcFile));
        //文件名
        String filename=srcFile.substring(srcFile.lastIndexOf("/")+1);
        //下载之后本地文件路径
        Path localDstPath = new Path(dstFile);

        FileSystem fileSystem = null;
        try {
            fileSystem = getFileSystem();
            fileSystem.copyToLocalFile(hdfsSrcPath,localDstPath);
        } catch (Exception e) {
            logger.error(MessageFormat.format("从HDFS下载文件至本地失败，srcFile:{0},dstFile:{1}",hdfsSrcPath,dstFile),e);
        }finally {
            close(fileSystem);
        }
        return localDstPath.toString();
    }

    /**
     * close方法
     */
    private void close(FileSystem fileSystem){
        if(fileSystem != null){
            try {
                fileSystem.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * 将相对路径转化为HDFS文件路径
     * @author xufu
     * @param dstPath 相对路径，比如：/data
     * @return java.lang.String
     */
    private String generateHdfsPath(String dstPath){
        String path = hdfsPath;
        if (dstPath.startsWith("hdfs")) {
            return dstPath;
        } else if (dstPath.startsWith("/")){
            path += dstPath;
        } else {
            path = path + "/" + dstPath;
        }

        return path;
    }

    @PostConstruct
    public void getPath() {
        hdfsPath = this.path;
    }

    @PostConstruct
    public void getName() {
        hdfsName = this.username;
    }

    public static String getHdfsPath() {
        return hdfsPath;
    }

    public String getUsername() {
        return username;
    }

}
