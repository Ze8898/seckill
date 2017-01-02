package com.six.seckill.dao.cache;

import com.six.seckill.dao.SeckillDao;
import com.six.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


/**
 * Created by liuze on 2016/12/30.
 * 配置spring 和junit整合 ,junit启动时加载spring IOC 容器
 * spring-test ,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
    private long id = 1001;
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void testSeckill() throws Exception {
        Seckill seckill = redisDao.getSeckill(id);
        if (seckill==null){
            seckill=seckillDao.queryById(id);
            if (seckill!=null){
                String result = redisDao.putSeckill(seckill);
                System.out.println(result);
                seckill = redisDao.getSeckill(id);
                System.out.println(seckill);
            }
        }
    }


}