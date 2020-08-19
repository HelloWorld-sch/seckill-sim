package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;

/**
 * 封装秒杀执行后的结果
 * @author shkstart
 * @create 2020-08-18 9:41
 */
public class SeckillExecution {

    //秒杀对象id
    private long seckillID;

    //秒杀执行结果状态
    private int state;

    //状态信息
    private String stateInfo;

    //秒杀成功对象
    private SuccessKilled successKilled;

    //秒杀成功
    public SeckillExecution(long seckillID, SeckillStatEnum seckillStatEnum, SuccessKilled successKilled) {
        this.seckillID = seckillID;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    //秒杀失败
    public SeckillExecution(long seckillID, SeckillStatEnum seckillStatEnum) {
        this.seckillID = seckillID;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
    }

    public long getSeckillID() {
        return seckillID;
    }

    public void setSeckillID(long seckillID) {
        this.seckillID = seckillID;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillID=" + seckillID +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
