package com.imoosen.util.junit;

import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Log4jConfigurer;

/**
 * Created by [mengsen] on 2017/7/18 0018.
 *
 * @Description: [一句话描述该类的功能]
 * @UpdateUser: [mengsen] on 2017/7/18 0018.
 *
 */

   /* @RunWith(SpringJUnit4ClassRunner.class)SpringJUnit支持，由此引入Spring-Test框架支持！

       @ContextConfiguration(locations = "classpath:applicationContext.xml")
       多个配置文件的话可以用数组表示{“applicationContext.xml”，“applicationContext1.xml”}；

        @ContextConfiguration("/spring-context.xml")放在根路径下（即类路径下），
        然后<import resource="spring-dao.xml" />所有的配置文件和资源文件

        @Transactional这个非常关键，如果不加入这个注解配置，事务控制就会完全失效！

       @TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
       这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），
       同时指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:config/spring/spring-*.xml"})
//这里可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SpringTest extends AbstractJUnit4SpringContextTests {
    static {
        System.setProperty("resources.config.path","E:\\idea\\Project");
        System.setProperty("app.config.model","springtestconfig");
        try {
            Log4jConfigurer.initLogging("file:${resources.config.path}/springtestconfig/log4j.xml");

        } catch (Exception e) {
            System.out.println("Cannot initialize log4j");
        }
    }
    public <T> T getBean(Class<T> type){
        return applicationContext.getBean(type);
    }
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();
    public Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }
    protected ApplicationContext getContext(){
        return applicationContext;
    }
}
