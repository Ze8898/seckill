package com.six.seckill.dao;


import com.six.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by liuze on 2016/10/19.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细 可过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone);

    /**
     * 根据ID查询SuccessKilled并携带秒杀对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWhithSeckill(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone);

}
