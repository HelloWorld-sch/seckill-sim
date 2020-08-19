package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author shkstart
 * @create 2020-08-18 13:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    /**
     * Closing non transactional SqlSession
     * list=[seckill{seckillid=1004, name='100元秒杀iPhoneXS', number=500, startTime=Sat Mar 28 13:00:00 CST 2020, endTime=Sun Mar 29 13:00:00 CST 2020, createTime=Sat Mar 28 10:13:38 CST 2020},
     * seckill{seckillid=1003, name='100元秒杀iPhoneX', number=400, startTime=Sat Mar 28 13:00:00 CST 2020, endTime=Sun Mar 29 13:00:00 CST 2020, createTime=Sat Mar 28 10:13:38 CST 2020},
     * seckill{seckillid=1002, name='100元秒杀iPhone8', number=300, startTime=Sat Mar 28 13:00:00 CST 2020, endTime=Sun Mar 29 13:00:00 CST 2020, createTime=Sat Mar 28 10:13:38 CST 2020},
     * seckill{seckillid=1001, name='100元秒杀iPhone7', number=200, startTime=Sat Mar 28 13:00:00 CST 2020, endTime=Sun Mar 29 13:00:00 CST 2020, createTime=Sat Mar 28 10:13:38 CST 2020}]
     */
    @Test
    public void getSeckillList() {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    /**
     *  Closing non transactional SqlSession
     *  seckill=seckill{seckillid=1000, name='100元秒杀iPhone6', number=99, startTime=Sat Mar 28 13:00:00 CST 2020, endTime=Sun Mar 29 13:00:00 CST 2020, createTime=Sat Mar 28 10:13:38 CST 2020}
     */
    @Test
    public void getById() {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
    }


    //集成测试测试代码完整逻辑，注意可重复执行
    //execution=SeckillExecution{seckillID=1001, state=1, stateInfo='秒杀成功',
    //successKilled=SuccessKilled{seckillId=1001, userpPhone=0, state=0, creatTime=null}}
    @Test
    public void testSeckillLogic() {
        long id = 1001L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()){
            logger.info("exposer={}",exposer);
            long phone = 18813100919L;
            String md5 = exposer.getMd5();
            //重复操作会抛出异常 需要使用try catch

            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
                logger.info("execution={}", seckillExecution);
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            }
        }
        else {
            logger.warn("exposer={}",exposer);
        }
    }

    // exposed=true, md5='473c88f97f28f0948db00e2075f1cc3e', seckillId=1000, now=0, start=0, end=0}
    /*@Test
    public void exportSeckillUrl() {
        long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);
    }*/

    /*
        execution=SeckillExecution{ seckillID=1000, state=1, stateInfo='秒杀成功',
        successKilled=SuccessKilled{seckillId=1000, userpPhone=0, state=0, creatTime=null}}
    */
    /*@Test
    public void executeSeckill() {
        long id = 1000L;
        long phone = 18813100919L;
        String md5 = "473c88f97f28f0948db00e2075f1cc3e";
        //重复操作会抛出异常 需要使用try catch
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
            logger.info("execution={}", seckillExecution);
        } catch (SeckillCloseException e) {
            logger.error(e.getMessage());
        } catch (RepeatKillException e) {
            logger.error(e.getMessage());
        }
    }*/


    @Test
    public void executeSeckillProcedure(){
        long seckillId = 1000;
        long phone = 13423456789L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()){
            String md5 = exposer.getMd5();
            SeckillExecution excution = seckillService.executeSeckillProcedure(seckillId,phone,md5);
            logger.info(excution.getStateInfo());
        }
    }
}