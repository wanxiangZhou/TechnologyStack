package com.touchgold.thinkinginjava.concurrency;

/**
 * @description: Demonstration of the Runnable interface
 * <p>
 * 1、线程可以驱动任务，因此需要一种描述任务的方式，例如实现Runnable接口。 2、实现Runnable接口的类，它必须具有run()，但run()并无特殊之处，它不会产生任何内在的线程能力。
 * 要实现线程行为，必须显示地将一个任务附着到线程上。重写run()，方法体即为任务描述。
 * </p>
 * @author: wanxiangZhou
 * @e-mail: jframe@163.com
 * @date: Created in 2020/2/19 10:44 上午
 */
public class LiftOff implements Runnable {

    // 复习：定义protected目的是SleepingTask.java子类对象能够访问，private是仅本类可见
    protected int countDown = 10;
    private static int taskCount = 0;
    // 非static修饰
    private final int id = taskCount++;

    public LiftOff(){
    }

    public LiftOff(int countDown){
        this.countDown = countDown;
    }

    public void status() {
        System.out.print("#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "),");
    }

    /**
     * 静态方法Thread.yield()的调用是对线程调度器的一种建议。线程调度器：Java线程机制的一部分，可以将CPU从一个线程转移到另外一个线程。
     */
    @Override
    public void run() {
        while (countDown-- > 0) {
            status();
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
    }
}
