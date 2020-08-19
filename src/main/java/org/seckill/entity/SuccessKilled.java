package org.seckill.entity;

import java.util.Date;

/**
 * @author shkstart
 * @create 2020-08-17 19:36
 */
public class SuccessKilled {

    private long seckillId;

    private long userpPhone;

    private short state;

    private Date creatTime;

    //多对一复合属性
    private Seckill seckill;

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserpPhone() {
        return userpPhone;
    }

    public void setUserpPhone(long userpPhone) {
        this.userpPhone = userpPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userpPhone=" + userpPhone +
                ", state=" + state +
                ", creatTime=" + creatTime +
                '}';
    }
}
