package com.touchgold.thinkinginjava.concurrency.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: Thinking In Java 4th Chapter Concurrency, Exercise 3, page 1124 <br/>
 * Repeat Exercise 1 using the different types of executors shown in this section.
 * @author: wanxiangZhou
 * @e-mail: jframe@163.com
 * @date: Created in 2020/2/20 4:54 下午
 */
public class Exercise3_Executor {

    public static void main(String[] args) {
        ExecutorService cached = Executors.newCachedThreadPool();
        ExecutorService fixed = Executors.newFixedThreadPool(5);
        ExecutorService single = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            cached.execute(new RunnerA());
            // fixed.execute(new RunnerA());
            // single.execute(new RunnerA());
        }
        cached.shutdown();
        fixed.shutdown();
        single.shutdown();
    }
}
