package com.atwzh.sell.lambda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wangzihang
 * @createTime 2019/6/28
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LambdaTest {


    @Test
    public void fun() {
        Comparator<Integer>com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {  //降序排列
                return Integer.compare(o2,o1);
            }
        };
        //lambda
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o2, o1);

        List<Integer> list = Arrays.asList(1,2,9,4,6);
        list.sort(comparator);
        System.out.println(list);
    }

    @Test
    public void fun2() {
        //将数组转换成集合的
        List<Employee> employees = Arrays.asList(
                new Employee("张三",23,3333.33),
                new Employee("李四",24,4444.44),
                new Employee("王五",25,5555.55),
                new Employee("赵六",26,6666.66),
                new Employee("田七",27,7777.77)
        );

        employees.stream().filter( (e) -> e.getSalary() < 4000 ).limit(2).forEach(System.out::println);
        System.out.println("------------------");
        employees.stream().filter( (e) -> e.getSalary() < 4000 ).filter((e) -> e.getAge() < 25).limit(2).forEach(System.out::println);

        System.out.println("------------------");
        employees.stream().map(Employee::getName).forEach(System.out::println); //打印所有的名字
        Stream<String> stringStream = employees.stream().map(Employee::getName);
        Object[] objects = stringStream.toArray();
        System.out.println(objects);
    }

    @Test
    public void fun3() {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());

        List<Integer> collect = outputStream.collect(Collectors.toList());


        System.out.println();
    }

    @Test
    public void fun4() {
        Stream<List<Employee>> inputStream = Stream.of(
                Arrays.asList( new Employee("张三",23,3333.33)),
                Arrays.asList( new Employee("李四",24,4444.44),
                        new Employee("王五",25,5555.55)),
                Arrays.asList(new Employee("赵六",26,6666.66),
                        new Employee("田七",27,7777.77))
        );
        Stream<Employee> outputStream = inputStream.
                flatMap(employeeList -> employeeList.stream());

        List<Employee> collect1 = outputStream.collect(Collectors.toList());


        System.out.println();
    }

    @Test
    public void fun5() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Optional<String> longest = stream.reduce((s1, s2) -> s1.length()>=s2.length() ? s1 : s2);
        //Optional<String> longest = stream.max((s1, s2) -> s1.length()-s2.length());
        Stream<String> stream2 = Stream.of("I", "love", "you", "too");
        Integer reduce = stream2.reduce(0, (sum, str) -> sum + str.length(), (a, b) -> a + b);

        System.out.println(longest.get());

    }

    @Test
    public void fun6() {
        // 求单词长度之和
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Integer lengthSum = stream.reduce(0,// 初始值　// (1)
        (sum, str) -> sum+str.length(), // 累加器 // (2)
                (a, b) -> a+b);// 部分和拼接器，并行执行时才会用到 // (3)
        // int lengthSum = stream.mapToInt(str -> str.length()).sum();
        System.out.println(lengthSum);
    }

}
