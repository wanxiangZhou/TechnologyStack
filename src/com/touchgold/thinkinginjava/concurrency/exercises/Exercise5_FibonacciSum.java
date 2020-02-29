package com.touchgold.thinkinginjava.concurrency.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description: Thinking In Java 4th Chapter Concurrency, Exercise 3, page 1125 <br/>
 * Modify Exercise 2 so that the task is a Callable that sums the values of all the Fibonacci numbers. Create several
 * tasks and display the results.
 * @author: wanxiangZhou
 * @e-mail: jframe@163.com
 * @date: Created in 2020/2/23 2:54 下午
 */
class FibonacciCallable implements Callable<Integer> {

    private final int number;

    public FibonacciCallable(int number){
        if (number > 0) {
            // 理论上number ≧ 3 才更能体现斐波那契数列
            this.number = number;
        } else {
            this.number = 1;
        }
    }

    @Override
    public Integer call() throws Exception {
        int sum = 1;
        // 声明为Integer类型，int类型会导致打印输出数组元素时，输出是封装类型对象引用地址哟，请问知道原因吗？
        Integer[] sequence = new Integer[number];
        sequence[0] = 1;
        if (number > 1) {
            sequence[1] = 1;
            sum += sequence[1];
        }
        if (number >= 3) {
            for (int i = 2; i < number; i++) {
                sequence[i] = sequence[--i] + sequence[--i];
                // 两次--i所以需要i+2后继续循环
                i = i + 2;
                sum += sequence[i];
            }
        }
        return sum;
    }
}

public class Exercise5_FibonacciSum {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Integer>> futureList = new ArrayList<>();
        /*for (int i = 0; i < 10; i++) {
            futureList.add(executor.submit(new FibonacciCallable(i)));
        }
        for (Future<Integer> future : futureList) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                executor.shutdown();
            }
        }*/
        // 一定要注意，任务结束的动作：
        // 1）Thread.yield()
        // 2）实现Callable接口调用ExecutorService.submit()方法确保Future是否已经完成？
        // 有多种写法，一种是submit的时候调用Thread.yield()，另外一种调用Future.get()或者Future.isDone()主动判断。
        for (int i = 0; i < 10; i++) {
            futureList.add(executor.submit(new FibonacciCallable(i)));
        }
        //另外：Thread.yield()若放置for循环内，又代表什么呢？
        Thread.yield();
        executor.shutdown();
        for (Future<Integer> future : futureList) {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        //Output: (Sample)
        // 1
        // 1
        // 2
        // 4
        // 7
        // 12
        // 20
        // 33
        // 54
        // 88
    }
}
