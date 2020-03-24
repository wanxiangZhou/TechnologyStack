package com.touchgold.thinkinginjava.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description: Calling sleep() to pause for a while.
 * @author: wanxiangZhou
 * @e-mail: jframe@163.com
 * @date: Created in 2020/2/29 11:26 上午
 */
public class SleepingTask extends LiftOff {

    @Override
    public void run() {
        // 复习：定义protected countDown 目的是SleepingTask.java子类对象能够访问，private则编译失败
        while (countDown-- > 0) {
            status();
            // Thread.yield();
            try {
                // Old-style
                // Thread.sleep(1000);
                // Java SE5/6-style
                TimeUnit.MICROSECONDS.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        // List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // 可以发现，submit有多个重载方法，允许接收Runnable、Callable
            // executor.submit(new SleepingTask());
            // executor.execute(new SleepingTask());
            // Output:(Sample)
            // #0(9),#1(9),#0(8),#2(9),#3(9),#1(8),#0(7),#4(9),#2(8),#3(8),
            // #4(8),#1(7),#2(7),#0(6),#3(7),#2(6),#1(6),#0(5),#4(7),#3(6),
            // #2(5),#1(5),#4(6),#0(4),#3(5),#2(4),#1(4),#4(5),#0(3),#3(4),
            // #1(3),#0(2),#3(3),#2(3),#4(4),#1(2),#0(1),#2(2),#3(2),#4(3),
            // #2(1),#1(1),#0(Liftoff!),#3(1),#4(2),#1(Liftoff!),#3(Liftoff!),
            // #4(1),#2(Liftoff!),#4(Liftoff!),
            try {
                System.out.println(executor.submit(new SleepingTask(), i + "").get());
                // Output:(Sample) 为什么这种情况，每次都是顺序执行？还是因为执行时间导致的看似"顺序执行"？
                // #0(9),#0(8),#0(7),#0(6),#0(5),#0(4),#0(3),#0(2),#0(1),#0(Liftoff!),0
                // #1(9),#1(8),#1(7),#1(6),#1(5),#1(4),#1(3),#1(2),#1(1),#1(Liftoff!),1
                // #2(9),#2(8),#2(7),#2(6),#2(5),#2(4),#2(3),#2(2),#2(1),#2(Liftoff!),2
                // #3(9),#3(8),#3(7),#3(6),#3(5),#3(4),#3(3),#3(2),#3(1),#3(Liftoff!),3
                // #4(9),#4(8),#4(7),#4(6),#4(5),#4(4),#4(3),#4(2),#4(1),#4(Liftoff!),4
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.out.println(e.getMessage());
            }
            // futureList.add(executor.submit(new SleepingTask(), i + ""));
        }
        // for (Future<String> future : futureList) {
        // try {
        // System.out.println(future.get());
        // // Output:(Sample)
        // // #0(9),#1(9),#2(9),#3(9),#0(8),#1(8),#2(8),#4(9),#3(8),#0(7),
        // // #1(7),#4(8),#2(7),#3(7),#1(6),#0(6),#2(6),#4(7),#3(6),#1(5),
        // // #2(5),#0(5),#4(6),#3(5),#4(5),#0(4),#1(4),#2(4),#3(4),#2(3),
        // // #4(4),#0(3),#1(3),#3(3),#2(2),#1(2),#4(3),#0(2),#3(2),#0(1),
        // // #2(1),#4(2),#3(1),#1(1),#0(Liftoff!),#2(Liftoff!),#1(Liftoff!),
        // // #3(Liftoff!),#4(1),#4(Liftoff!),0
        // // 1
        // // 2
        // // 3
        // // 4
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // } catch (ExecutionException e) {
        // System.out.println(e.getMessage());
        // }
        // }
        executor.shutdown();
    }
}
