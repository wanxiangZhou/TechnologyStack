package com.touchgold.thinkinginjava.concurrency.exercises;

import java.util.Arrays;
import java.util.stream.Stream;

import com.zwx.thinkinginjava.test.RecursionToIterate;

/**
 * @description: Thinking In Java 4th Chapter Concurrency, Exercise 2, page 1120 <br/>
 * Following the form of generics/Fibonacci.java, create a task that produces a sequence of n Fibonacci numbers, where n
 * is provided to the constructor of the task. Create a number of these tasks and drive them using threads.
 * @author: wanxiangZhou
 * @e-mail: jframe@163.com
 * @date: Created in 2020/2/19 3:36 下午
 */
public class Exercise2_Fibonacci {

    public static void main(String[] args) {
        for (int i = 1; i < 8; i++) {
            // new Thread(new Fibonacci(i)).start();
            new Thread(new Fibonacci4Recursion(i)).start();
        }
    }
}

/**
 * 斐波那契数列定义：F(1)=1，F(2)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 3，n ∈ N*）
 */
class Fibonacci implements Runnable {

    // 定义为final，非static，必须要initialized，直接赋初始值或构造器方法初始化，否则编译不通过
    private final int number;

    public Fibonacci(int number){
        if (number > 0) {
            // 理论上number ≧ 3 才更能体现斐波那契数列
            this.number = number;
        } else {
            this.number = 1;
        }
    }

    @Override
    public void run() {
        // 声明为Integer类型，int类型会导致打印输出数组元素时，输出是封装类型对象引用地址哟，请问知道原因吗？
        Integer[] sequence = new Integer[number];
        sequence[0] = 1;
        if (number > 1) {
            sequence[1] = 1;
        }
        if (number >= 3) {
            for (int i = 2; i < number; i++) {
                sequence[i] = sequence[--i] + sequence[--i];
                // 两次--i所以需要i+2后继续循环
                i = i + 2;
            }
        }
        // 循环打印输出数组元素的方式：
        // Arrays.asList(sequence).stream().forEach(e -> System.out.println(e));
        // 循环打印输出数组元素的方式：这种最便捷
        Stream.of(sequence).forEach(System.out::println);
        // 打印输出数组对象，并不能输出数组元素
        // Collections.singleton(sequence).forEach(System.out::println);
        System.out.println(Arrays.toString(sequence));
        Thread.yield();
        // Output: (Sample)
        // 1
        // 1
        // 1
        // [1, 1]
        // 1
        // 1
        // 1
        // 1
        // 2
        // 3
        // 2
        // [1]
        // [1, 1, 2]
        // [1, 1, 2, 3]
        // 执行多次可能会发现，序列按照其长度从小到大顺序输出的【概率】还挺大的，其实是因为元素越多，生成时间肯定越长，就越靠后
        // [1]
        // [1, 1]
        // [1, 1, 2]
        // [1, 1, 2, 3]
        // [1, 1, 2, 3, 5]
        // [1, 1, 2, 3, 5, 8]
        // [1, 1, 2, 3, 5, 8, 13]
        // [1, 1, 2, 3, 5, 8, 13, 21]
        // [1, 1, 2, 3, 5, 8, 13, 21, 34]
        // [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
        // [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89]
        // [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144]
        // [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233]
        // [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377]
    }
}

/**
 * 思考：很多案例当中，都采用递归方法来生成斐波那契数列 </br>
 * 其实递归方法与迭代循环相比，谁执行效率更优？如何优化？为什么说避免使用递归？还是以偏概全？
 * 
 * @see RecursionToIterate
 */
class Fibonacci4Recursion implements Runnable {

    private int count;
    private final int n;

    public Fibonacci4Recursion(int n){
        this.n = n;
    }

    public int next() {
        return fib(count++);
    }

    private int fib(int count) {
        if (count < 2) {
            return 1;
        }
        return fib(--count) + fib(--count);
    }

    @Override
    public void run() {
        Integer[] sequence = new Integer[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = next();
        }
        System.out.println(Arrays.toString(sequence));
    }
}
