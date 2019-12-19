package com.zhansheng.faultdetection.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zhansheng.faultdetection.dao.BaseMapper;
import com.zhansheng.framework.domain.FaultDetection.AbnormalParticulars;
import com.zhansheng.framework.domain.FaultDetection.NormalParticulars;
import com.zhansheng.framework.domain.FaultDetection.TbParticulars;
import com.zhansheng.framework.domain.FaultDetection.TbState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/8/15
 */


@Service
public class BaseService {

    @Autowired
    private BaseMapper baseMapper;

    @Autowired
    MongoTemplate mongoTemplate;








    /**
     * 机组健康度总览 (查询所有模型 最新一条数据)
     *
     * @param draught_number
     * @return
     */
    public TbState indexState(Integer draught_number) {
        Query query = new Query()
                .addCriteria(Criteria.where("fDraughtNumber").is(draught_number)).limit(1);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.findOne(query, TbState.class);
    }


    //查询一条详情数据
    public TbParticulars particularOne(Integer draughtNumber) {
        Query query = new Query()
                .addCriteria(Criteria.where("fDraughtNumber").is(draughtNumber)).limit(1);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fDateAcqTime")));
        return mongoTemplate.findOne(query, TbParticulars.class);
    }


    // base状态
    public List<TbState> bladeState(Integer draught_number) {
        Query query = new Query()
                .addCriteria(Criteria.where("fDraughtNumber").is(draught_number)).limit(1);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.find(query, TbState.class);
    }


    // 叶根螺栓断裂
    public TbState monitorStateOne(Integer draughtNumber) {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState1", true);
        fieldsObject.put("fProgressState1", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fDraughtNumber").is(draughtNumber)).limit(1);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.findOne(query, TbState.class);
    }


    //查询风机详情
    public List<TbParticulars> particular(Integer draughtNumber) {
        Query query = new Query()
                .addCriteria(Criteria.where("fDraughtNumber").is(draughtNumber)).limit(1);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fDateAcqTime")));
        return mongoTemplate.find(query, TbParticulars.class);
    }





    // 叶片结冰
    public TbState bladeList(Integer draughtNumber) {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState2", true);
        fieldsObject.put("fProgressState2", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fDraughtNumber").is(draughtNumber)).limit(1);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.findOne(query, TbState.class);
    }


    // 偏航对风
    public TbState excursionOne(Integer draughtNumber) {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState4", true);
        fieldsObject.put("fProgressState4", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fDraughtNumber").is(draughtNumber)).limit(1);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.findOne(query, TbState.class);
    }


    //查询模型数据正常数据   叶片结冰
    public List<TbState> findfnormalState2() {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState2", true);
        fieldsObject.put("fProgressState2", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fState2").is(0)).limit(20);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.find(query, TbState.class);
    }

    //查询模型数据不正常数据   叶片结冰
    public List<TbState> findabnormalfState2() {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState2", true);
        fieldsObject.put("fProgressState2", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fState2").nin(0)).limit(20);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.find(query, TbState.class);
    }

    //查询模型数据正常数据   叶片结冰
    public List<TbParticulars> findParticulars(Integer draughtNumber) {
        Query query = new Query()
                .addCriteria(Criteria.where("fDraughtNumber").is(draughtNumber)).limit(10);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fDateAcqTime")));
        return mongoTemplate.find(query, TbParticulars.class);
    }


    //查询叶根螺栓断裂正常详情
    public List<TbState> findfnormalState1() {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState1", true);
        fieldsObject.put("fProgressState1", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fState1").is(0)).limit(20);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.find(query, TbState.class);
    }

    //查询叶根螺栓断裂不正常详情
    public List<TbState> findabnormalfState1() {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState1", true);
        fieldsObject.put("fProgressState1", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fState1").nin(0)).limit(20);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.find(query, TbState.class);
    }


    //查询叶片结冰正常详情
    public List<TbState> findfnormalState4() {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState4", true);
        fieldsObject.put("fProgressState4", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fState4").is(0)).limit(20);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.find(query, TbState.class);
    }

    //查询叶片结冰不正常详情
    public List<TbState> findabnormalfState4() {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState4", true);
        fieldsObject.put("fProgressState4", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fState4").nin(0)).limit(20);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.find(query, TbState.class);
    }

    //如果叶片结冰的数据为空
    public List<AbnormalParticulars> findNot() {
        Query query = new Query().limit(10);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fDateAcqTime")));
        return mongoTemplate.find(query, AbnormalParticulars.class);
    }

    public List<NormalParticulars> findYes() {
        Query query = new Query().limit(10);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fDateAcqTime")));
        return mongoTemplate.find(query, NormalParticulars.class);
    }

}


//查询机组劣化正常详情
    /*public List<TbState> findfnormalState3() {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState3", true);
        fieldsObject.put("fProgressState3", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fState3").is(0)).limit(20);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.find(query, TbState.class);
    }*/

//叶片结冰状态值(百分比)曲线图
    /*public List<TbState> findBydraughtNumberStateList(int draughtNumber) {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState2", true);
        fieldsObject.put("fProgressState2", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fDraughtNumber").is(draughtNumber)).limit(20);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.find(query, TbState.class);
    }*/
//偏航对风曲线图(百分比)
    /*public List<TbState> excursionList(int draughtNumber) {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState4", true);
        fieldsObject.put("fProgressState4", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fDraughtNumber").is(draughtNumber)).limit(20);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.find(query, TbState.class);
    }*/
//叶根螺栓断裂曲线图 (百分比)
    /*public List<TbState> monitorStateLimit(int draughtNumber) {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState1", true);
        fieldsObject.put("fProgressState1", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fDraughtNumber").is(draughtNumber)).limit(20);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.find(query, TbState.class);
    }*/
// 叶根螺栓断裂结冰 和 也片结冰  首页展示
    /*public List<TbState> monitorList(Integer draughtNumber) {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState2", true);
        fieldsObject.put("fProgressState2", true);
        fieldsObject.put("fState1", true);
        fieldsObject.put("fProgressState1", true);
        fieldsObject.put("fDateAcqTime", true);

        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fDraughtNumber").is(draughtNumber)).limit(1);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fDateAcqTime")));
        return mongoTemplate.find(query, TbState.class);
    }*/
// 机组劣化
   /* public TbState degradationOne(Integer draughtNumber) {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState3", true);
        fieldsObject.put("fProgressState3", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fDraughtNumber").is(draughtNumber)).limit(1);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.findOne(query, TbState.class);
    }

    //机组劣化曲线图
    public List<TbState> degradationList(int draughtNumber) {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState3", true);
        fieldsObject.put("fProgressState3", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fDraughtNumber").is(draughtNumber)).limit(20);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.find(query, TbState.class);
    }*/
//查询机组劣化不正常详情
    /*public List<TbState> findabnormalfState3() {
        DBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fDraughtNumber", true);
        fieldsObject.put("fState3", true);
        fieldsObject.put("fProgressState3", true);
        fieldsObject.put("fCreateTime", true);
        BasicDBObject dbObject = new BasicDBObject();
        Query query = new BasicQuery(dbObject.toString(), fieldsObject.toString())
                .addCriteria(Criteria.where("fState3").nin(0)).limit(20);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "fCreateTime")));
        return mongoTemplate.find(query, TbState.class);
    }*/