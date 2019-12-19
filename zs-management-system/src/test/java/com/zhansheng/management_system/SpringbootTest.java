package com.zhansheng.management_system;

import com.zhansheng.framework.domain.UserManager.Industrialcontrol.TbDictKey;
import com.zhansheng.management_system.service.BaseServiceImpl;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    private BaseServiceImpl baseService;


    @Test
    public void pageListRedis1() {

        List<TbDictKey> d = baseService.findByKey("AITopic");
        System.out.println(d);


    }


}