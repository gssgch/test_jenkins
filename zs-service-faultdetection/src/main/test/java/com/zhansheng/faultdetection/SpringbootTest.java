package com.zhansheng.faultdetection;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.zhansheng.faultdetection.service.BaseService;
import com.zhansheng.faultdetection.service.BladeServiceImpl;
import com.zhansheng.framework.domain.FaultDetection.TbParticulars;
import com.zhansheng.framework.domain.FaultDetection.TbState;
import com.zhansheng.framework.domain.Results.Result;
import com.zhansheng.framework.domain.UserManager.request.DraListDto;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author
 * @description
 * @date 2019/8/13
 */


@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootTest {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BladeServiceImpl bladeService;

    @Test
    public void findNumberListNew() {
        DraListDto draListDto = new DraListDto();
        draListDto.setDraughtNumber(33);
        draListDto.setStartTime("2017/7/1 16:00:00");
        draListDto.setOverTime("2017/7/1 01:00:00");

        List<TbParticulars> list = bladeService.findNumberListNew1(draListDto);
        System.out.println("---------------" + list);


    }


    @Test
    public void test3() {
        Query query = new Query();
//            query.addCriteria(new Criteria("_id").is("5d37b774da876c373017c671"));
        query.addCriteria(new Criteria("F_Draught_number").is(1001));
        TbState t_state = mongoTemplate.findOne(query, TbState.class, "t_state");
        System.out.println(t_state);
    }


    @Test
    public void test() {
        int draughtNumber = 1001;
        Query query = new Query().addCriteria(Criteria.where("F_Draught_number").is(draughtNumber)).limit(1);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "F_CreateTime")));
        System.out.println(query);
        List<TbState> list = mongoTemplate.find(query, TbState.class);
        System.out.println(list);
    }


    @Test
    public void test1() {
        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient("192.168.1.14", 27017);

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("bolt");
            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("t_state");

            //检索所有文档
            /**
             * 1. 获取迭代器FindIterable<Document>
             * 2. 获取游标MongoCursor<Document>
             * 3. 通过游标遍历检索出的文档集合
             * */


            FindIterable<Document> d2 = collection.find(new Bson() {
                @Override
                public <TDocument> BsonDocument toBsonDocument(Class<TDocument> aClass, CodecRegistry codecRegistry) {
                    return null;
                }
            });
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                System.out.println(mongoCursor.next());
            }

            System.out.println("集合 test 选择成功");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }


    @Test
    public void v() {
        BigDecimal bd = new BigDecimal(113.45611);
        Double tem = bd.setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
        System.out.println(tem);
    }


    @Autowired
    private BaseService baseService;

    @Test
    public void s() {
        TbState state = baseService.bladeList(33);
        System.out.println("-------------------" + "-------------------");
        System.out.println("-------------------" + state + "-------------------");
        TbParticulars particulars = baseService.particularOne(33);
        System.out.println("-------------------" + "-------------------");
        System.out.println("-------------------" + particulars + "-------------------");


    }


}