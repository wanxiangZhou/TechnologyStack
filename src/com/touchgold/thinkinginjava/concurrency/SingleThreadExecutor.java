package com.touchgold.thinkinginjava.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * <p>
 * SingleThreadExecutor 会自动序列化提交的多个任务，每个人物都会在下一个任务开始之前运行结束，所有的任务都是用相同的线程。
 * </p>
 * @author: wanxiangZhou
 * @e-mail: jframe@163.com
 * @date: Created in 2020/2/20 4:00 下午
 */
public class SingleThreadExecutor {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }
}
