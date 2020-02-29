package com.touchgold.thinkinginjava.concurrency.exercises;

/**
 * @description: 这里用一句话描述这个类的作用
 * @author: wanxiangZhou
 * @e-mail: jframe@163.com
 * @date: Created in 2020/2/19 2:52 下午
 */
class Printer implements Runnable {

    private static int taskCount;
    private final int id = taskCount++;

    Printer(){
        System.out.println("Printer started, ID = " + id);
    }

    public void run() {
        System.out.println("Stage 1..., ID = " + id);
        Thread.yield();
        System.out.println("Stage 2..., ID = " + id);
        Thread.yield();
        System.out.println("Stage 3..., ID = " + id);
        Thread.yield();
        System.out.println("Printer ended, ID = " + id);
    }
}

public class Test {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new Thread(new Printer()).start();
    }
}
