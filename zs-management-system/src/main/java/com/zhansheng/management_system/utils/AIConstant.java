package com.zhansheng.management_system.utils;

/**
 * Created by Huige
 * Email: 824203453@qq.com
 * DATE: 2019/12/4
 * Desc:
 */
public class AIConstant {

    // 原始字段选取的key核心部分
    public static String AI_ORGSELECT = "_orgslt_";
    // 特征工程的key核心部分
    public static String AI_FEATUREENG = "_feaeng_";
    // 特征选取的key 核心部分
    public static String AI_FEATURESELECT = "_feaslt_";
    // 算法key 核心部分
    public static String AI_SF = "_sf_";
    // 参数key 核心部分
    public static String AI_PARAM = "_param_";

    // 模型 的迭代次数
    public static String AI_MODEL = "";

    // 把各种表的key以 hash格式存储到redis中。 redis的主键设置为 AI_ALL_KEYS
    public static String AI_ALL_KEYS = "AI_ALL_KEYS";

    // 路径
    /**
     * 所有数据存储的前缀
     * 前缀：
     * /home/shqd/001/
     * 专题/
     * 最原始数据存储路径：
     * 专题/org/
     * 专题/org/20191204/
     * 原始数据选取后的临时数据存储路径：
     * 专题/orgselect/原始数据选取的key/
     * 特征提取函数脚本存放路径：
     * 专题/featurescript/专题_时间戳.py
     * 特征工程数据文件存储路径:
     * 专题/feature/特征工程key/
     * <p>
     * 特征选取数据存储路径：
     * 专题/createdataset/split/特征选取key/份数/
     * 1.csv
     * 2.csv
     * 生成训练集和测试集的数据存储路径：
     * 专题/createdataset/traintest/特征选取key/份数/测试集编号/
     * train.csv
     * test.csv
     * 模型的存储路径：
     * 专题/model/算法名称/模型编号/
     * eg:
     * 001/model/001_sf_300/001_tzxq_10:001_sf_300:20191112:001/
     * 算法脚本存储路径：
     * 专题/algoscript/算法编号:算法名称.py
     */

    public static String PATH_PREFIX = "/home/shqd/";

    public static String PATH_ALGOSCRIPT = "/algoscript/";

    public static String EXTENSION = ".py";

    // 模型可视化 数据库表的key 的数字补位，  1 ---> 00000001
    // 设计的key位数是8位
    public static String  AI_KEY_CONVERING =  "%08d";

}
