package com.zhansheng.framework.domain.UserManager.ext;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.csvreader.CsvWriter;

/**
 * 支持csv文件导出
 * @author xufu
 * @version
 */
public class CSVUtils<T> {

    /** logger */
    private static final Log    logger              = LogFactory.getLog(CSVUtils.class);

    /** 定义 限制条件 */
    private static final String MS_CSV_CONTENT_TYPE = "Content-Type: text/csv";

    /**
     * LimitDefine
     * @author xufu
     * @version
     */
    static public class LimitDefine {

        /** 文件类型 */
        private static String FILE_TYPE = "csv";

    }


    public static Map<String,Object> object2Map(Object object) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        //获得类的的属性名 数组
        Field[] fields = object.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                String name = new String(field.getName());
                result.put(name, field.get(object));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //风机故障数量统计导出（维度为年）
    public List<String[]> map2List(Collection<T> list) {
        List<String[]> dataList = new ArrayList();
        for (T a: list) {
            Map<String, String> map = new HashMap<String, String>();
            Map<String, Object> mapObject = new HashMap<String, Object>();
            mapObject = object2Map(a);
            Map tempMap = mapObject;
            map = tempMap;
            List<String> mapValuesList = new ArrayList<String>(map.values());
            List<String> newMapValuesList = new ArrayList<String>();
            for(Object b : mapValuesList){
                String c = b.toString();
                newMapValuesList.add(c);
            }
            String[] arr = new String[newMapValuesList.size()];
            newMapValuesList.toArray(arr);
            dataList.add(arr);
        }
        return dataList;
    }

    /**
     *
     * @param headArray
     * @param dataListTarget
     * @param fileName
     * @param response
     */
    public void exportCsv(String[] headArray, Collection<T> dataListTarget, String fileName,
                          HttpServletResponse response) {
        OutputStream os = null;
        List<String[]> dataList = new ArrayList();
        dataList = map2List(dataListTarget);
        try {
            if (StringUtils.isEmpty(fileName)) {
                fileName = UUID.randomUUID().toString();
            }
            String exportFileName = new String(fileName.getBytes("gbk"), "ISO8859-1");
            String exportName = exportFileName.concat(".").concat(LimitDefine.FILE_TYPE);
            response.setContentType(MS_CSV_CONTENT_TYPE);
            response.setHeader("Content-disposition", "attachment;filename=" + exportName);
            response.setHeader("Cache-Control", "must-revalidate,post-check=0,pre-check=0");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "public");
            os = response.getOutputStream();
            CsvWriter wr = new CsvWriter(os, ',', Charset.forName("GBK"));
            // 写入表头的数据
            if (null != headArray && headArray.length > 0) {
                wr.writeRecord(headArray);
            }
            // 写入内容数据
            if (!CollectionUtils.isEmpty(dataList)) {
                for (String[] data : dataList) {
                    if (null != data && data.length > 0) {
                        wr.writeRecord(data);
                    }
                }
            }
            wr.close();
            os.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
