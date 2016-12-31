package com.six.seckill.dao;

import com.six.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by liuze on 2016/12/30.
 * 配置spring 和junit整合 ,junit启动时加载spring IOC 容器
 * spring-test ,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SuccessKilledDao successKilledDao;
    @Test
    public void insertSuccessKilled() throws Exception {
        long id = 1001L;
        long phone=13933322456L;
        int insertCount=   successKilledDao.insertSuccessKilled(id,phone);
        System.out.println("insertCount"+insertCount);

    }


    /**
     * SuccessKilled{seckillId=1000, userPhone=13933322456, state=-1, createTime=Sat Dec 31 17:37:51 CST 2016}
     Seckill{seckillId=1000, name='1000元秒杀iphone6', number=99, startTime=Sat Dec 31 17:30:08 CST 2016, endTime=Sun Jan 01 00:00:00 CST 2017, createTime=Sat Dec 31 13:23:29 CST 2016}
     * @throws Exception
     */
    @Test
    public void queryByIdWhithSeckill() throws Exception {
        long id = 1001L;
        long phone=13933322456L;
        SuccessKilled successKilled=successKilledDao.queryByIdWhithSeckill(id,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}