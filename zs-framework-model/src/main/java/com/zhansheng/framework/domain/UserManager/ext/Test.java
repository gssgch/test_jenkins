package com.zhansheng.framework.domain.UserManager.ext;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * author: Kwein
 * Date:2019/10/18
 * Time:10:05
 */
public class Test {

    public static void main(String[] args) throws Exception {
        /*ExportExcelUtil<Student> util = new ExportExcelUtil<Student>();
        // 准备数据
        List<Student> list = new ArrayList<Student>();

        String flag = "ymd";

        for (int i = 0; i < 10; i++) {
            list.add(new Student(111, "zs","男"));
            list.add(new Student(111, "zs","男"));
            list.add(new Student(111,"ls", "女"));
            // 风机编号  年  月  日  发电量    5个参数构造

            // 风机编号  年  月  发电量         4个
//            flag = "ym";

            // 风机编号  年  发电量             3个

//            flag = "y";
        }

//        if(flag.equals("ymd")){
//
//            String[] columnNames = {"ID","name","性别"};
//        }
        String[] columnNames = {"ID","name","性别"};
        util.exportExcel("用户导出", columnNames, list, new FileOutputStream
                ("C:\\Users\\Kwein\\Desktop\\test22.xls"), ExportExcelUtil.EXCEL_FILE_2003);*/
    }
}
