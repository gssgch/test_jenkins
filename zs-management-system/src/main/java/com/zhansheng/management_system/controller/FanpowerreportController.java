package com.zhansheng.management_system.controller;

import com.zhansheng.api.Industrialcontrol.FanpowerreportControllerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanpowerreport;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.request.PowerrePort;
import com.zhansheng.framework.domain.UserManager.ext.*;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.dao.TbFanpowerreportMapper;
import com.zhansheng.management_system.service.TbFanpowerreportServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @author xuzhengjie
 * @description
 * @date 2019/9/30 10:03
 */


@RequestMapping("/Fanpowerreport")
@RestController
public class FanpowerreportController implements FanpowerreportControllerApi {


    @Autowired
    private TbFanpowerreportServiceImpl fanpowerreportService;


    @Autowired
    private TbFanpowerreportMapper fanpowerreportMapper;

    private CSVUtils csvUtils;


    @Override
    @GetMapping("/findByYMD")
    public Result findByYMD(PowerrePort powerrePort, PageParam pageParam) {
        if (powerrePort == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return fanpowerreportService.findByYMD(powerrePort, pageParam);
    }

    @Override
    @LogAnnotation
    @ApiOperation(value = "文件导出")
    @GetMapping("/findListExcel")
    public Result findListExcel(PowerrePort powerrePort, HttpServletRequest request, HttpServletResponse response, int exportType) {
        if (powerrePort == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //条件查询 导出excel
        List<TbFanpowerreport> list = fanpowerreportService.getFanpowerreportList(powerrePort);

        List<FanpowerreportYear> listyear = null;

        List<FanpowerreportMonth> listmonth = null;

        List<FanpowerreportDay> listday = null;

        // 维度： 1 ：年  2 ：月  3:日
        int types = powerrePort.getYmd();
        String[] columnNames = {"风机编号", "年", "月", "日", "发电量"};
        if (types == 2) {
            columnNames = new String[]{"风机编号", "年", "月", "发电量"};

            listmonth = new ArrayList<>();
            for (TbFanpowerreport tb : list) {
                listmonth.add(tb.transmonth(tb));
            }
        } else if (types == 1) {
            columnNames = new String[]{"风机编号", "年", "发电量"};

            listyear = new ArrayList<>();
            for (TbFanpowerreport tb : list) {
                listyear.add(tb.transyear(tb));
            }
        } else if (types == 3){
            columnNames = new String[]{"风机编号", "年", "月", "日", "发电量"};
            listday = new ArrayList<>();
            for (TbFanpowerreport tb : list) {

                listday.add(tb.transday(tb));
            }
        }
        FileOutputStream oos = null;

        try {
            //判断是查询的哪一个
            if (types == 1) {

                String fileName = "发电量年报表";
                if (exportType == 2) {
                    CSVUtils<FanpowerreportYear> util = new CSVUtils<FanpowerreportYear>();
                    util.exportCsv(columnNames, listyear, fileName, response);
                } else {
                    oos = new FileOutputStream(fileName);
                    ExportExcelWrapper<FanpowerreportYear> util = new ExportExcelWrapper<FanpowerreportYear>();
                    util.exportExcel(fileName, columnNames, listyear, oos, ExportExcelUtil.EXCEL_FILE_2003);
                    response.reset(); // 非常重要
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
                    OutputStream out = response.getOutputStream();

                    ExportExcelUtil.returnwb.write(out);
                    out.flush();
                    out.close();
                }

            } else if (types == 2) {
                String fileName = "发电量月报表";
                if (exportType == 2) {
                    CSVUtils<FanpowerreportMonth> util = new CSVUtils<FanpowerreportMonth>();
                    util.exportCsv(columnNames, listmonth, fileName, response);
                } else {
                    oos = new FileOutputStream(fileName);
                    ExportExcelUtil<FanpowerreportMonth> util = new ExportExcelUtil<FanpowerreportMonth>();
                    util.exportExcel("用户导出", columnNames, listmonth, oos, ExportExcelUtil.EXCEL_FILE_2003);
                    response.reset(); // 非常重要
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
                    OutputStream out = response.getOutputStream();
                    ExportExcelUtil.returnwb.write(out);
                    out.flush();
                    out.close();
                }

            } else {
                String fileName = "发电量日报表";
                if (exportType == 2) {
                    CSVUtils<FanpowerreportDay> util = new CSVUtils<FanpowerreportDay>();
                    util.exportCsv(columnNames, listday, fileName, response);
                } else {
                    oos = new FileOutputStream(fileName);
                    ExportExcelUtil<FanpowerreportDay> util = new ExportExcelUtil<FanpowerreportDay>();
                    util.exportExcel("用户导出", columnNames, listday, oos, ExportExcelUtil.EXCEL_FILE_2003);
                    response.reset(); // 非常重要
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
                    OutputStream out = response.getOutputStream();
                    ExportExcelUtil.returnwb.write(out);
                    out.flush();
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new Result(true, StatusCode.OK, "查询成功 !");
    }
}
