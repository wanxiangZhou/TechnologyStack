package com.ghoul.thinkinginjava.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 这里用一句话描述这个类的作用
 * @author: wanxiangZhou
 * @e-mail: jframe@163.com
 * @date: Created in 2020/2/20 11:18 上午
 */
public class FixedThreadPool {

    public static void main(String[] args) {
        // Constructor argument is number of Threads.
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }
    // Output:(Sample)
    // #0(9),#1(9),#2(9),#3(9),#4(9),#0(8),#1(8),#4(8),#1(7),#3(8),
    // #4(7),#3(7),#2(8),#4(6),#2(7),#3(6),#1(6),#0(7),#2(6),#4(5),
    // #1(5),#2(5),#0(6),#1(4),#3(5),#4(4),#2(4),#0(5),#1(3),#3(4),
    // #4(3),#2(3),#0(4),#1(2),#3(3),#4(2),#2(2),#0(3),#1(1),#4(1),
    // #3(2),#2(1),#1(Liftoff!),#0(2),#4(Liftoff!),#3(1),#2(Liftoff!),#0(1),#3(Liftoff!),#0(Liftoff!),
}
