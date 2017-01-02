package com.six.seckill.service;

import com.six.seckill.dto.Exposer;
import com.six.seckill.dto.SeckillExecution;
import com.six.seckill.entity.Seckill;
import com.six.seckill.exception.RepeatKillException;
import com.six.seckill.exception.SeckillCloseException;
import com.six.seckill.exception.SeckillException;

import java.util.List;

/**
 * 业务接口: 站在"使用者"角度设计接口
 * 三个方面: 方法定义粒度, 参数, 返回类型 (return 类型/异常)
 * Created by liuze on 2016/12/31.
 */
public interface SeckillService {
    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口的地址,
     * 否则输出系统开始时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);


    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone , String md5)
            throws SeckillException,RepeatKillException,SeckillCloseException;

    /**
     * 执行秒杀操作 By 存储过程
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone , String md5)
            throws SeckillException,RepeatKillException,SeckillCloseException;

}
