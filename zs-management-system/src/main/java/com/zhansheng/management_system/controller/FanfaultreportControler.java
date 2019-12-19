package com.zhansheng.management_system.controller;

import com.zhansheng.api.Industrialcontrol.FanfaultreportControlerApi;
import com.zhansheng.framework.domain.Entity.StatusCode;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbDictKey;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFanfaultreport;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbFaultLoc;
import com.zhansheng.framework.domain.UserManager.Industrialcontrol.request.PowerrePort;
import com.zhansheng.framework.domain.UserManager.ext.*;
import com.zhansheng.framework.domain.UserManager.request.PageParam;
import com.zhansheng.framework.exception.ExceptionCast;
import com.zhansheng.framework.model.response.CommonCode;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.BaseServiceImpl;

import com.zhansheng.management_system.service.TbFanfaultreportServiceImpl;

import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @author xuzhengjie
 * @description
 * @date 2019/10/21 10:08
 */


@RestController
@RequestMapping("/Fanfaultreport")
public class FanfaultreportControler implements FanfaultreportControlerApi {

    @Autowired
    private BaseServiceImpl baseService;

    @Autowired
    private TbFanfaultreportServiceImpl fanfaultreportService;

    private CSVUtils csvUtils;

    @Override
    @GetMapping("/findBysum")
    public Result findByYMD(PowerrePort powerrePort, PageParam pageParam) {
        if (powerrePort == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return fanfaultreportService.findByYMD(powerrePort, pageParam);
    }

    @LogAnnotation
    @ApiOperation(value = "文件导出")
    @GetMapping("/findnumExcelreport")
    public Result findListExcel(PowerrePort powerrePort, HttpServletRequest request, HttpServletResponse response, int exportType) {
        if (powerrePort == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //条件查询 导出excel
        List<TbFanfaultreport> list = fanfaultreportService.getFanfaultreportList(powerrePort);

        List<FanfaultreportYear> listyear = null;

        List<FanfaultreportMonth> listmonth = null;

        List<FanfaultreportDay> listday = null;

        // 维度： 1 ：年  2 ：月  3:日
        int types = powerrePort.getYmd();
        String[] columnNames = {"风机编号", "年", "月", "日", "故障数"};
        if (types == 2) {
            columnNames = new String[]{"风机编号", "年", "月", "故障数"};

            listmonth = new ArrayList<>();
            for (TbFanfaultreport tb : list) {
                listmonth.add(tb.transmonth(tb));
            }
        } else if (types == 1) {
            columnNames = new String[]{"风机编号", "年", "故障数"};

            listyear = new ArrayList<>();
            for (TbFanfaultreport tb : list) {
                listyear.add(tb.transyear(tb));
            }
        } else if (types == 3){
            columnNames = new String[]{"风机编号", "年", "月", "日", "故障数"};
            listday = new ArrayList<>();
            for (TbFanfaultreport tb : list) {
                listday.add(tb.transday(tb));
            }
        }
        FileOutputStream oos = null;
        try {

            //判断是查询的哪一个
            if (types == 1) {
                String fileName = "故障数量年报表.xls";
                if (exportType == 2) {
                    fileName = "故障数量年报表";
                    CSVUtils<FanfaultreportYear> util = new CSVUtils<FanfaultreportYear>();
                    util.exportCsv(columnNames, listyear, fileName, response);
                } else {
                    oos = new FileOutputStream(fileName);
                    ExportExcelWrapper<FanfaultreportYear> util = new ExportExcelWrapper<FanfaultreportYear>();
                    util.exportExcel(fileName, columnNames, listyear, oos, ExportExcelUtil.EXCEL_FILE_2003);
                    response.reset(); // 非常重要
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1") + ".xlsx");
                    OutputStream out = response.getOutputStream();

                    ExportExcelUtil.returnwb.write(out);
                    out.flush();
                    out.close();
                }

            } else if (types == 2) {
                String fileName = "故障数量月报表.xls";
                if (exportType == 2) {
                    fileName = "故障数量月报表";
                    CSVUtils<FanfaultreportMonth> util = new CSVUtils<FanfaultreportMonth>();
                    util.exportCsv(columnNames, listmonth, fileName, response);
                } else {
                    oos = new FileOutputStream(fileName);
                    ExportExcelUtil<FanfaultreportMonth> util = new ExportExcelUtil<FanfaultreportMonth>();
                    util.exportExcel("用户导出", columnNames, listmonth, oos, ExportExcelUtil.EXCEL_FILE_2003);
                    response.reset(); // 非常重要
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1") + ".xlsx");
                    OutputStream out = response.getOutputStream();

                    ExportExcelUtil.returnwb.write(out);
                    out.flush();
                    out.close();
                }
            } else {
                String fileName = "故障数量日报表.xls";
                if (exportType == 2) {
                    fileName = "故障数量日报表";
                    CSVUtils<FanfaultreportDay> util = new CSVUtils<FanfaultreportDay>();
                    util.exportCsv(columnNames, listday, fileName, response);
                } else {
                    oos = new FileOutputStream(fileName);
                    ExportExcelUtil<FanfaultreportDay> util = new ExportExcelUtil<FanfaultreportDay>();
                    util.exportExcel("用户导出", columnNames, listday, oos, ExportExcelUtil.EXCEL_FILE_2003);
                    response.reset(); // 非常重要
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1") + ".xlsx");
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

    @Override
    @GetMapping("/findByloc")
    public Result findByloc(PowerrePort powerrePort, PageParam pageParam) {
        if (powerrePort == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return fanfaultreportService.findByloc(powerrePort, pageParam);
    }

    /**
     *  通过接口获取到 风机故障数据，然后对故障位置编码和 故障位置名称 添加到map集合中
     * @return
     */
    private HashMap<Integer, String> getfanlocMap() {
        HashMap<Integer, String> fanlocmap = new HashMap<>();
        List<TbFaultLoc> locList = baseService.getLocList();
        for (TbFaultLoc t:locList) {
            fanlocmap.put(t.getFaultLocNum(),t.getFaultLoc());
        }
        return fanlocmap;
    }

    /**
     *  通过接口获取到 风机故障数据，然后对故障等级编码和故障级别 添加到map集合中
     * @return
     */
    private HashMap<Integer,String> getfanlevelMap() {
        HashMap<Integer,String> fanlevelmap = new HashMap<>();
        List<TbDictKey> rank = baseService.findByKey("Rank");
        for (TbDictKey t:rank) {
            fanlevelmap.put(t.getFDictkey(),t.getFDictvalue());
        }
        return fanlevelmap;
    }

    @LogAnnotation
    @ApiOperation(value = "文件导出")
    @GetMapping("/findlocExcelreport")
    public Result findlocreport(PowerrePort powerrePort, HttpServletRequest request, HttpServletResponse response, int exportType) {
        if (powerrePort == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //条件查询 导出excel
        List<TbFanfaultreport> list = fanfaultreportService.getFanfaultreportListLoc(powerrePort);

        List<FanfaultLocYear> listyear = null;

        List<FanfaultLocMonth> listmonth = null;

        List<FanfaultLocDay> listday = null;

        // 维度： 1 ：年  2 ：月  3:日
        int types = powerrePort.getYmd();
        String[] columnNames = {"风机编号", "年", "月", "日","故障位置", "故障数"};
        //更换故障位置的map
        HashMap<Integer, String> fanlocmap = getfanlocMap();

        if (types == 2) {
            //fanlocmap.size();
            columnNames = new String[]{"风机编号", "年", "月","故障位置", "故障数"};
            listmonth = new ArrayList<>();
            for (TbFanfaultreport tb : list) {

                listmonth.add(tb.translocmonth(tb,fanlocmap));
            }
        } else if (types == 1) {
            columnNames = new String[]{"风机编号", "年","故障位置", "故障数"};
            listyear = new ArrayList<>();
            for (TbFanfaultreport tb : list) {
                listyear.add(tb.translocyear(tb,fanlocmap));
            }
        } else if (types == 3){
            columnNames = new String[]{"风机编号", "年", "月", "日","故障位置", "故障数"};
            listday = new ArrayList<>();
            for (TbFanfaultreport tb : list) {

                listday.add(tb.translocday(tb,fanlocmap));
            }
        }
        FileOutputStream oos = null;
        try {
            //判断是查询的哪一个
            if (types == 1) {
                String fileName = "故障位置年报表.xls";
                if (exportType == 2) {
                    fileName = "故障位置年报表";
                    CSVUtils<FanfaultLocYear> util = new CSVUtils<FanfaultLocYear>();
                    util.exportCsv(columnNames, listyear, fileName, response);
                } else {
                    oos = new FileOutputStream(fileName);
                    ExportExcelWrapper<FanfaultLocYear> util = new ExportExcelWrapper<FanfaultLocYear>();
                    util.exportExcel(fileName, columnNames, listyear, oos, ExportExcelUtil.EXCEL_FILE_2003);
                    response.reset(); // 非常重要
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1") + ".xlsx");
                    OutputStream out = response.getOutputStream();

                    ExportExcelUtil.returnwb.write(out);
                    out.flush();
                    out.close();
                }
            } else if (types == 2) {
                String fileName = "故障位置月报表.xls";
                if (exportType == 2) {
                    fileName = "故障位置月报表";
                    CSVUtils<FanfaultLocMonth> util = new CSVUtils<FanfaultLocMonth>();
                    util.exportCsv(columnNames, listmonth, fileName, response);
                } else {
                    oos = new FileOutputStream(fileName);
                    ExportExcelUtil<FanfaultLocMonth> util = new ExportExcelUtil<FanfaultLocMonth>();
                    util.exportExcel("用户导出", columnNames, listmonth, oos, ExportExcelUtil.EXCEL_FILE_2003);
                    response.reset(); // 非常重要
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1") + ".xlsx");
                    OutputStream out = response.getOutputStream();

                    ExportExcelUtil.returnwb.write(out);
                    out.flush();
                    out.close();
                }
            } else {
                String fileName = "故障位置日报表.xls";
                if (exportType == 2) {
                    fileName = "故障位置日报表";
                    CSVUtils<FanfaultLocDay> util = new CSVUtils<FanfaultLocDay>();
                    util.exportCsv(columnNames, listday, fileName, response);
                } else {
                    oos = new FileOutputStream(fileName);
                    ExportExcelUtil<FanfaultLocDay> util = new ExportExcelUtil<FanfaultLocDay>();
                    util.exportExcel("用户导出", columnNames, listday, oos, ExportExcelUtil.EXCEL_FILE_2003);
                    response.reset(); // 非常重要
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1") + ".xlsx");
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



    @Override
    @LogAnnotation
    @ApiOperation(value = "文件导出")
    @GetMapping("/findBylevel")
    public Result findBylevel(PowerrePort powerrePort, PageParam pageParam) {
        if (powerrePort == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return fanfaultreportService.findBylevel(powerrePort, pageParam);
    }

    @GetMapping("/findlevelExcelreport")
    public Result findLevelreport(PowerrePort powerrePort, HttpServletRequest request, HttpServletResponse response, int exportType) {
        if (powerrePort == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //条件查询 导出excel
        List<TbFanfaultreport> list = fanfaultreportService.getFanfaultreportListLevel(powerrePort);

        List<FanfaultLevelYear> listyear = null;

        List<FanfaultLevelMonth> listmonth = null;

        List<FanfaultLevelDay> listday = null;

        //更换故障等级的map
        HashMap<Integer,String> fanlevelmap = getfanlevelMap();
        // 维度： 1 ：年  2 ：月  3:日
        int types = powerrePort.getYmd();
        String[] columnNames = {"风机编号", "年", "月", "日","故障等级", "故障数"};
        if (types == 2) {
            columnNames = new String[]{"风机编号", "年", "月","故障等级", "故障数"};

            listmonth = new ArrayList<>();
            for (TbFanfaultreport tb : list) {
                listmonth.add(tb.translevelmonth(tb,fanlevelmap));
            }
        } else if (types == 1) {
            columnNames = new String[]{"风机编号", "年","故障等级", "故障数"};

            listyear = new ArrayList<>();
            for (TbFanfaultreport tb : list) {
                listyear.add(tb.translevelyear(tb,fanlevelmap));
            }
        } else if (types == 3){
            columnNames = new String[]{"风机编号", "年", "月", "日","故障等级", "故障数"};
            listday = new ArrayList<>();
            for (TbFanfaultreport tb : list) {

                listday.add(tb.translevelday(tb,fanlevelmap));
            }
        }
        FileOutputStream oos = null;
        try {
            List<String[]> dataList = new ArrayList();
            //判断是查询的哪一个
            if (types == 1) {
                String fileName = "故障等级年报表.xls";
                if (exportType == 2) {
                    fileName = "故障等级年报表";
                    CSVUtils<FanfaultLevelYear> util = new CSVUtils<FanfaultLevelYear>();
                    util.exportCsv(columnNames, listyear, fileName, response);
                } else {
                    oos = new FileOutputStream(fileName);
                    ExportExcelWrapper<FanfaultLevelYear> util = new ExportExcelWrapper<FanfaultLevelYear>();
                    util.exportExcel(fileName, columnNames, listyear, oos, ExportExcelUtil.EXCEL_FILE_2003);
                    response.reset(); // 非常重要
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1") + ".xlsx");
                    OutputStream out = response.getOutputStream();

                    ExportExcelUtil.returnwb.write(out);
                    out.flush();
                    out.close();
                }
            } else if (types == 2) {
                String fileName = "故障等级月报表.xls";
                if (exportType == 2) {
                    fileName = "故障等级月报表";
                    CSVUtils<FanfaultLevelMonth> util = new CSVUtils<FanfaultLevelMonth>();
                    util.exportCsv(columnNames, listmonth, fileName, response);
                } else {
                    oos = new FileOutputStream(fileName);
                    ExportExcelUtil<FanfaultLevelMonth> util = new ExportExcelUtil<FanfaultLevelMonth>();
                    util.exportExcel("用户导出", columnNames, listmonth, oos, ExportExcelUtil.EXCEL_FILE_2003);
                    response.reset(); // 非常重要
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1") + ".xlsx");
                    OutputStream out = response.getOutputStream();

                    ExportExcelUtil.returnwb.write(out);
                    out.flush();
                    out.close();
                }
            } else {
                String fileName = "故障等级日报表.xls";
                if (exportType == 2) {
                    fileName = "故障等级日报表";
                    CSVUtils<FanfaultLevelDay> util = new CSVUtils<FanfaultLevelDay>();
                    util.exportCsv(columnNames, listday, fileName, response);
                } else {
                    oos = new FileOutputStream(fileName);
                    ExportExcelUtil<FanfaultLevelDay> util = new ExportExcelUtil<FanfaultLevelDay>();
                    util.exportExcel("用户导出", columnNames, listday, oos, ExportExcelUtil.EXCEL_FILE_2003);
                    response.reset(); // 非常重要
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1") + ".xlsx");
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
