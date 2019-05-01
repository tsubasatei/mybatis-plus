package com.xt.mp.test;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xt.mp.bean.Employee;
import com.xt.mp.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

/**
 * 测试
 */
public class MyTest {

    private ApplicationContext iocContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = iocContext.getBean("employeeMapper", EmployeeMapper.class);

    /**
     * 条件构造器  删除操作
     */
    @Test
    public void testEntityWrapperDelete() {
        employeeMapper.delete(new EntityWrapper<Employee>()
                .like("last_name", "老师")
                .eq("age", 28));
    }

    /**
     * 条件构造器  修改操作
     */
    @Test
    public void testEntityWrapperUpdate() {
        Employee employee = new Employee();
        employee.setLastName("naruto");
        employee.setAge(16);
        employee.setEmail("naruto@163.com");

        employeeMapper.update(employee, new EntityWrapper<Employee>()
                .like("last_name", "老师")
                .eq("age", 30));
    }

    /**
     * 条件构造器   查询操作
     */
    @Test
    public void testEntityWrapperSelect() {
        //我们需要分页查询tbl_employee表中，年龄在18~50之间且性别为男且姓名为Tom的所有用户
//        List<Employee> employees = employeeMapper.selectPage(new Page<>(1, 2),
//                new EntityWrapper<Employee>()
//                        .between("age", 18, 25)
//                        .eq("gender", 1)
//                        .eq("last_name", "Tom"));
//        List<Employee> employees = employeeMapper.selectPage(new Page<Employee>(1, 2),
//                Condition.create()
//                        .between("age", 18, 25)
//                        .eq("gender", 1)
//                        .eq("last_name", "Tom"));
//        System.out.println(employees);

        // 查询tbl_employee表中， 性别为女并且名字中带有"老师" 或者  邮箱中带有"a"
//        List<Employee> employees = employeeMapper.selectList(new EntityWrapper<Employee>()
//                .eq("gender", 0)
//                .like("last_name", "老师")
////                .or() // WHERE (gender = ? AND last_name LIKE ? OR email LIKE ?)
//                .orNew() // WHERE (gender = ? AND last_name LIKE ?) OR (email LIKE ?)
//                .like("email", "a"));
//        System.out.println(employees);

        // 查询性别为女的, 根据age进行排序(asc/desc), 简单分页
        List<Employee> employees = employeeMapper.selectList(new EntityWrapper<Employee>()
                .eq("gender", 0)
//                .orderBy("age", true)
//                .last("limit 1, 5")

//                .orderBy("age")
				.orderDesc(Arrays.asList(new String [] {"age"}))
//				.last("desc limit 1,3")
        );
        System.out.println(employees);
    }

    /**
     * 通用 删除操作
     */
    @Test
    public void testCommonDelete() {
        //1 .根据id进行删除
//        Integer result = employeeMapper.deleteById(10);
//        System.out.println("result: " + result);

        //2. 根据 条件进行删除
//        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put("last_name", "EE");
//        columnMap.put("gender", 1);
//        Integer result = employeeMapper.deleteByMap(columnMap);
//		System.out.println("result: " + result );

        //3. 批量删除
        List<Integer> list = List.of(5, 6, 7, 8);
        Integer result = employeeMapper.deleteBatchIds(list);
        System.out.println("result: " + result );
    }

    /**
     * 通用 查询操作
     */
    @Test
    public void  testCommonSelect() {
        //1. 通过id查询
//        Employee employee = employeeMapper.selectById(1);
//        System.out.println(employee);

        //2. 通过多个列进行查询    id  +  lastName
//        Employee employee = new Employee();
//        employee.setId(1);
//        employee.setLastName("Tom");
//        Employee emp = employeeMapper.selectOne(employee);
//        System.out.println(emp);

        //3. 通过多个id进行查询    <foreach>
//        List<Integer> ids = Arrays.asList(1, 2, 3, 4);
//        List<Employee> employees = employeeMapper.selectBatchIds(ids);
//        System.out.println(employees);

        //4. 通过Map封装条件查询
//        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put("last_name", "EE");
//        columnMap.put("gender", 1);
//        List<Employee> employees = employeeMapper.selectByMap(columnMap);
//        System.out.println(employees);

        //5. 分页查询
        List<Employee> employees = employeeMapper.selectPage(new Page<>(2, 2), null);
        System.out.println(employees);
    }

    /**
     * 通用 更新操作
     */
    @Test
    public void testCommonUpdate() {
        Employee employee = new Employee();
        employee.setId(10);
        employee.setLastName("xt");
        employee.setGender(1);
        employee.setEmail("xt@163.com");
//        employee.setAge(30);

        // 只修改非空的列
        Integer result = employeeMapper.updateById(employee);
        // 修改所有的列， 无值设置空
//        Integer result = employeeMapper.updateAllColumnById(employee);
        System.out.println("result: " + result);


    }

    /**
     * 通用 插入 操作
     */
    @Test
    public void testCommonInsert () {

        Employee employee = new Employee();
        employee.setAge(18);
        employee.setEmail("ee@163.com");
//        employee.setGender(1);
//        employee.setLastName("EE");
        employee.setSalary(20000.0);

        //插入到数据库
        // insert方法在插入时， 会根据实体类的每个属性进行非空判断，只有非空的属性对应的字段才会出现到SQL语句中
//        Integer result = employeeMapper.insert(employee);

        //insertAllColumn方法在插入时， 不管属性是否非空， 属性所对应的字段都会出现到SQL语句中.
        Integer result = employeeMapper.insertAllColumn(employee);
        System.out.println("result: " + result);

        //获取当前数据在数据库中的主键值
        Integer id = employee.getId();
        System.out.println("ID: " + id);
    }

    @Test
    public void testEnvironment() throws Exception{
        DataSource ds = iocContext.getBean("dataSource", DataSource.class);
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
