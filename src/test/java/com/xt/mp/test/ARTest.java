package com.xt.mp.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xt.mp.bean.Employee;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 测试
 */
public class ARTest {

    private ApplicationContext iocContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    /**
     * AR  分页复杂操作
     */
    @Test
    public void  testARPage() {
        Employee employee = new Employee();
        Page<Employee> page = employee.selectPage(new Page<>(1, 5), new EntityWrapper<Employee>().eq("gender", 0));
        List<Employee> employees = page.getRecords();
        System.out.println(employees);
    }

    /**
     * AR 删除操作
     *
     * 注意: 删除不存在的数据 逻辑上也是属于成功的.
     */
    @Test
    public void testARDelete() {
        Employee employee = new Employee();
//        boolean result = employee.deleteById(6);
//        employee.setId(6);
//        boolean result = employee.deleteById();
//        System.out.println("result: " + result);

        boolean result = employee.delete(new EntityWrapper<Employee>()
                .like("last_name", "s"));
        System.out.println("result: " + result);


    }
    
    /**
     * AR 查询操作
     */
    @Test
    public void testARSelect() {
        Employee employee = new Employee();
////        Employee employee1 = employee.selectById(6);
//        employee.setId(6);
//        Employee employee1 = employee.selectById();
//        System.out.println(employee1);

//        List<Employee> employees = employee.selectAll();
//        System.out.println(employees);

        List<Employee> employees = employee.selectList(new EntityWrapper<Employee>()
                .like("last_name", "s"));
        System.out.println(employees);

        int count = employee.selectCount(new EntityWrapper<Employee>()
                .like("last_name", "s"));
        System.out.println(count);

    }

    /**
     * AR 修改操作
     */
    @Test
    public void testARUpdate() {
        Employee employee = new Employee();
        employee.setId(6);
        employee.setLastName("宋老湿");
        employee.setEmail("sls@atguigu.com");
        employee.setGender(1);
        employee.setAge(36);


        boolean result = employee.updateById();
        System.out.println("result:" +result );
    }

    /**
     * AR  插入操作
     */
    @Test
    public void  testARInsert() {
        Employee employee = new Employee();
        employee.setAge(18);
        employee.setLastName("sakura");
        employee.setEmail("sakura@163.com");
        employee.setGender(1);

        boolean result = employee.insert();
        System.out.println("插入成功：" + result);
    }
    
}
