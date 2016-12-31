package com.six.seckill.exception;

/**
 * Created by liuze on 2016/12/31.
 * 秒相关业务异常
 */
public class SeckillException extends RuntimeException{

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
