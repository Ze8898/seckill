package com.six.seckill.dao;

import com.six.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by liuze on 2016/12/30.
 * 配置spring 和junit整合 ,junit启动时加载spring IOC 容器
 * spring-test ,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {


    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;



    @Test
    public void queryById() throws Exception {
        long id = 1002;
        Seckill seckill= seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {

        List<Seckill> seckills=seckillDao.queryAll(0,100);
        for (Seckill seckill:seckills){
            System.out.println(seckill);
        }

    }
    @Test
    public void reduceNumber() throws Exception {
        Date killTime = new Date();
        int updateCount =seckillDao.reduceNumber(1000L,killTime);
        System.out.println("updateCount"+updateCount);

    }
}