package com.xt.mp.test;

import com.baomidou.mybatisplus.plugins.Page;
import com.xt.mp.bean.Employee;
import com.xt.mp.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 测试 插件
 */
public class PluginTest {

    private ApplicationContext iocContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = iocContext.getBean("employeeMapper", EmployeeMapper.class);

    /**
     * 测试 乐观锁插件
     */

    @Test
    public void testOptimisticLocker() {
        //更新操作
        Employee employee = new Employee();
        employee.setId(14);
        employee.setLastName("TomAA123");
        employee.setEmail("tomAA@sina.com");
        employee.setGender(1);
        employee.setAge(22);
        employee.setVersion(2);

        employeeMapper.updateById(employee);
    }

    /**
     * 测试 性能分析插件
     */
    @Test
    public void testPerformance() {
        Employee employee = new Employee();
        employee.setLastName("玛利亚老师");
        employee.setEmail("mly@sina.com");
        employee.setGender(0);
        employee.setAge(22);

        employeeMapper.insert(employee);
    }

    /**
     * 测试SQL执行分析插件
     */
    @Test
    public void testSQLExplain() {
        employeeMapper.delete(null); // 全表删除
    }

    /**
     * 测试分页插件
     */
    @Test
    public void testPage() {

        Page<Employee> page = new Page<>(1, 3);
        List<Employee> employees = employeeMapper.selectPage(page, null);
        System.out.println(employees);

        System.out.println("===============获取分页相关的一些信息======================");
        System.out.println("总条数:" + page.getTotal());
        System.out.println("当前页码: "+ page.getCurrent());
        System.out.println("总页码:" + page.getPages());
        System.out.println("每页显示的条数:" + page.getSize());
        System.out.println("是否有上一页: " + page.hasPrevious());
        System.out.println("是否有下一页: " + page.hasNext());

        //将查询的结果封装到page对象中
        page.setRecords(employees);
    }
}
