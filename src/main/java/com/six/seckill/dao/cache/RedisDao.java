package com.six.seckill.dao.cache;

import com.six.seckill.entity.Seckill;
import com.sun.org.apache.regexp.internal.RE;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by liuze on 2017/1/1.
 */
public class RedisDao {

    private final JedisPool jedisPool;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public RedisDao(String ip,int port) {
        this.jedisPool = new JedisPool(ip,port);
    }

    private RuntimeSchema<Seckill> schema =RuntimeSchema.createFrom(Seckill.class);

    public Seckill getSeckill(long seckillId){
        //redis 操作逻辑

        try {
            Jedis jedis= jedisPool.getResource();
            try {
                String key = "seckill:"+seckillId;
                //Jedis 并没有实现内部序列化操作
                //get-->byte//-->反序列化 --<Object(Seckill)
                //采用自定义序列化的方式
                //protostuff: pojo.

                byte[] bytes=jedis.get(key.getBytes());
                //缓存中获取到
                if (bytes!=null){
                    Seckill seckill = schema.newMessage(); //空对象
                    ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);  //Seckill被反序列化
                    return seckill;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    public String putSeckill(Seckill seckill){
        //set Object(Seckill) --> 序列化 -->byte[]
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:"+seckill.getSeckillId();
                byte[] bytes =ProtostuffIOUtil.toByteArray(seckill,schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存
                int timeout = 60 * 60 ; //亿小时
                String result = jedis.setex(key.getBytes(),timeout,bytes);
                return result;
            }finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
