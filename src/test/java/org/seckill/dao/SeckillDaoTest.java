package org.seckill.dao;

import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.seckill.entity.Seckill;
        import org.springframework.test.context.ContextConfiguration;
        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

        import javax.annotation.Resource;

        import java.util.Date;
        import java.util.List;

        import static org.junit.Assert.*;

/**
 * 首先需要配置spring 和 junit 整合，为了junit启动时加载springIOC容器，spring-test,junit
 * @author shkstart
 * @create 2020-08-17 21:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring 配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    /**
     * 1.关于mysql8.0的JDBC连接Mysql 8.0.12版本的几个注意事项：https://www.jianshu.com/p/582ae6d91154
     * 2.　在系统中也有个username属性，这时系统变量覆盖了Properties中的值，这时取得username的值为系统的用户名Administrator，
     *      密码为properties中的password去查询数据库，此时用户名名和密码并不匹配就会报错。
     *      在Spring完成注入时是用 "${..}"  方式获取值完成注入的。而通过这种表达式也能直接获取到JVM系统属性
     *   建议：username是敏感词汇，为了安全起见还是尽量不要使用username。
     *
     *   ：https://www.cnblogs.com/tongxuping/p/7081656.html
     */


    /**
     * Caused by: org.apache.ibatis.binding.BindingException:
     * Parameter 'offset' not found. Available parameters are [0, 1, param1, param2]
     *    List<Seckill> queryAll(int offset , int limit);a
     *    java没有保存行参的表述
     */
    @Test
    public void queryById() {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    /**
     * Caused by: org.springframework.core.NestedIOException:
     * Failed to parse mapping resource:
     * 'file [/Users/yoyo/IdeaProjects/seckill/target/classes/mapper/SeckillDao.xml]';
     * nested exception is org.apache.ibatis.builder.BuilderException: Error creating document instance.
     * Cause: org.xml.sax.SAXParseException; lineNumber: 30; columnNumber: 10; XML 文档结构必须从头至尾包含在同一个实体内。
     */
    @Test
    public void queryAll() {
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for (Seckill seckill:seckills) {
            System.out.println(seckill);
        }
    }

    @Test
    public void reduceNumber() {
        Date killTime =new Date();
        int updateCount = seckillDao.reduceNumber(1000L,killTime);
        System.out.println("updateCount=" + updateCount);
    }
}