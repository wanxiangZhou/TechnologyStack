package com.touchgold.thinkinginjava.concurrency;

/**
 * @description: The most basic use of the Thread class.
 * @author: wanxiangZhou
 * @e-mail: jframe@163.com
 * @date: Created in 2020/2/19 11:32 上午
 */
public class BasicThreads {

    public static void main(String[] args) {
        // Thread t = new Thread(new LiftOff());
        // // 每执行一次，控制台输出结果可能都不一样
        // t.start();
        // System.out.println("Waiting for LiftOff");
        for (int i = 0; i < 5; i++) {
            Thread to = new Thread(new LiftOff());
            // 每执行一次，控制台输出结果可能都不一样
            to.start();
            System.out.println("Waiting for LiftOff");
        }
    }
}
