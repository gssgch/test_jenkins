package com.zhansheng.management_system.utils;

import javafx.geometry.Point2D;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 文件共通方法
 */
@Service
public class FileUtils {

    /**
     * 保存文件
     * @param path path
     * @throws IOException IOException
     */
    public void write(String path, String contents) throws IOException {
        //将写入转化为流的形式
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            //一次写一行
            String ss = contents;
            bw.write(ss);
            bw.newLine();  //换行用
            //关闭流
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String path, String contents) throws IOException {
        /*BufferedWriter bw = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            for(Point2D p : this.allpoint)
            {
                String s = String.valueOf(p.getX());
                bw.write(s);
                bw.write(' ');
                s = String.valueOf(p.getY());
                bw.write(s);
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        String totalString = "";
        try {
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if(line == 1) {
                    totalString = tempString;
                    line++;
                } else {
                    totalString = totalString + "\n" + tempString ;
                }
            }
            //System.out.print(totalString);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return totalString;
    }

    public static void main(String[] args) {
        //test
        String fileName = "D:/workfile/华电项目/101_sf_00000001.py";
        FileUtils.readFileByLines(fileName);
    }
}