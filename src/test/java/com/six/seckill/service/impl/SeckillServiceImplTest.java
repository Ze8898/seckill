package com.six.seckill.service.impl;

import com.six.seckill.dto.Exposer;
import com.six.seckill.dto.SeckillExecution;
import com.six.seckill.entity.Seckill;
import com.six.seckill.exception.RepeatKillException;
import com.six.seckill.exception.SeckillCloseException;
import com.six.seckill.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by liuze on 2016/12/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info(list.toString());


    }

    @Test
    public void getById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info(seckill.toString());
    }


    /**
     * 测试代码完整逻辑,注意可重复执行
     *
     * @throws Exception
     */
    @Test
    public void exportSeckillLogic() throws Exception {
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            long phone = 13502171816L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
                logger.info(execution.toString());
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }
        } else {
            /**
             * 秒杀未开启
             */
            logger.info(exposer.toString() + "秒杀未开启");

        }

    }

    @Test
    public void executeSeckillProcedure() {
        long seckillId = 1005;
        long phone = 13933324556L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution execution=seckillService.executeSeckillProcedure(seckillId, phone, md5);
            logger.info(execution.getStateInfo());
        }

    }

}