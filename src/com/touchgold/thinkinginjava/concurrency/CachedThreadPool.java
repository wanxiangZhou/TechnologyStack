package com.touchgold.thinkinginjava.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: Executor执行器用来管理Thread对象，从而简化并发编程
 * @author: wanxiangZhou
 * @e-mail: jframe@163.com
 * @date: Created in 2020/2/20 11:10 上午
 */
public class CachedThreadPool {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executor.execute(new LiftOff());
        }
        // shutdown()方法的调用可以防止新任务被提前交给这个Executor，当前线程（在本例中，即驱动main()的线程）
        // 将继续运行在shutdown()被调用之前提交的所有任务。这个程序将在Executor中的所有任务完成之后尽快退出。
        executor.shutdown();
    }
    // Output:(Sample)
    // #0(9),#1(9),#2(9),#3(9),#4(9),#0(8),#1(8),#2(8),#3(8),#4(8),
    // #0(7),#1(7),#2(7),#3(7),#4(7),#0(6),#1(6),#0(5),#1(5),#4(6),
    // #1(4),#2(6),#3(6),#4(5),#1(3),#0(4),#4(4),#3(5),#2(5),#4(3),
    // #0(3),#1(2),#3(4),#2(4),#4(2),#0(2),#1(1),#3(3),#2(3),#4(1),
    // #0(1),#1(Liftoff!),#3(2),#2(2),#4(Liftoff!),#0(Liftoff!),#3(1),#2(1),#3(Liftoff!),#2(Liftoff!),
}
