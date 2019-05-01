package com.xt.mp.test;

import com.xt.mp.bean.User;
import com.xt.mp.mapper.EmployeeMapper;
import com.xt.mp.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试
 */
public class InjectTest {

    private ApplicationContext iocContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = iocContext.getBean("employeeMapper", EmployeeMapper.class);
    private UserMapper userMapper = iocContext.getBean("userMapper", UserMapper.class);

    /**
     * 测试 Oracle 主键 Sequence
     */
    @Test
    public void testOracle() {
        User user = new User();
        user.setLogicFlag(1);
        user.setName("OracleSEQ");
        userMapper.insert(user);
    }

    /**
     * 测试公共字段填充
     */
    @Test
    public void testMetaObjectHandler() {
        User user = new User();
//        user.setLogicFlag(1);
//        user.setName("to");
//        userMapper.insert(user);

        user.setId(5);
        userMapper.updateById(user);
    }

    /**
     * 测试逻辑删除
     */
    @Test
    public void testLogicDelete() {
        Integer result = userMapper.deleteById(4);
        System.out.println("result: " + result);

        User user = userMapper.selectById(4);
        System.out.println(user);
    }

    /**
     * 测试自定义全局操作
     */
    @Test
    public void  testMySqlInjector() {
        int result = employeeMapper.deleteAll();
        System.out.println("result: " + result);
    }
}
