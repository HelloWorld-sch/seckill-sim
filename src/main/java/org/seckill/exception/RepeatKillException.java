package org.seckill.exception;

/**
 * 重复秒杀异常（运行时异常）
 * @author shkstart
 * @create 2020-08-18 9:52
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
