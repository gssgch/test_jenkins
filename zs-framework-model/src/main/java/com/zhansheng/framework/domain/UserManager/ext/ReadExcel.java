package com.zhansheng.framework.domain.UserManager.ext;

import com.zhansheng.framework.domain.Results.UserCode;
import com.zhansheng.framework.exception.ExceptionCast;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.Collator;
import java.util.*;

/**
 * @author
 * @description
 * @date 2019/8/21
 */

public class ReadExcel {

    // 总行数
    private int totalRows = 0;
    // 总条数
    private int totalCells = 0;
    // 错误信息接收器
    private String errorMsg;

    // 构造方法
    public ReadExcel() {
    }

    // 获取总行数
    public int getTotalRows() {
        return totalRows;
    }

    // 获取总列数
    public int getTotalCells() {
        return totalCells;
    }

    // 获取错误信息
    public String getErrorInfo() {
        return errorMsg;
    }

    /**
     * 读EXCEL文件，获取信息集合
     *
     * @param fielName
     * @return
     */
    public List<Map<String, Object>> getExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();// 获取文件名
        //        List<Map<String, Object>> userList = new LinkedList<Map<String, Object>>();
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            return createExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据excel里面的内容读取客户信息
     *
     * @param is          输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public List<Map<String, Object>> createExcel(InputStream is, boolean isExcel2003) {
        try {
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            return readExcelValue(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取Excel里面客户的信息
     *
     * @param wb
     * @return
     */
    private List<Map<String, Object>> readExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            // 循环Excel的列
            Map<String, Object> map = new HashMap<String, Object>();
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        // 如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String windmill = String.valueOf(cell.getNumericCellValue());
                            map.put("windmill", windmill.substring(0, windmill.length() - 2 > 0 ? windmill.length() - 2 : 1));// 风厂名称
                        } else {
                            map.put("windmill", cell.getStringCellValue());// 风厂名称
                        }
                    } else if (c == 1) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String loopnmae = String.valueOf(cell.getNumericCellValue());
                            map.put("loopnmae", loopnmae.substring(0, loopnmae.length() - 2 > 0 ? loopnmae.length() - 2 : 1));// 环路名称
                        } else {
                            map.put("loopnmae", cell.getStringCellValue());// 环路名称
                        }
                    } else if (c == 2) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String number = String.valueOf(cell.getNumericCellValue());
                            map.put("number", number.substring(0, number.length() - 2 > 0 ? number.length() - 2 : 1));// 编号
                        } else {
                            map.put("number", cell.getStringCellValue());// 风机编号
                        }
                    }
                }
            }
            // 添加到list
            userList.add(map);
        }

        //把execl相同的字段排序在一起
        if (userList != null) {
            Collections.sort(userList, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    Collator instance = Collator.getInstance(Locale.CHINA);
                    return instance.compare(o1.get("loopnmae").toString(), o2.get("loopnmae").toString());
                }
            });
        }

        return userList;
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            ExceptionCast.cast(UserCode.USER_ADDPAGE_EXCEL);
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    // @描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


}
