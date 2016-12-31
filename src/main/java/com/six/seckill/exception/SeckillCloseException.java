package com.six.seckill.exception;

/**
 * Created by liuze on 2016/12/31.
 * 秒杀关闭异常
 */
public class SeckillCloseException extends RuntimeException{

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
