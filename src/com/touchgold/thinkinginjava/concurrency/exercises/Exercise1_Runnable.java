package com.touchgold.thinkinginjava.concurrency.exercises;

/**
 * @description: Thinking In Java 4th Chapter Concurrency, Exercise 1, page 1120 <br/>
 * Implement a Runnable. Inside run(), print a message, and then call yield(). Repeat this three times, and then return
 * from run(). Put a startup message in the constructor and a shutdown message when the task terminates. Create a number
 * of these tasks and drive them using threads.
 * @author: wanxiangZhou
 * @e-mail: jframe@163.com
 * @date: Created in 2020/2/19 2:35 下午
 */
public class Exercise1_Runnable {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new RunnerA()).start();
        }
    }
}

class RunnerA implements Runnable {

    private static int taskCount;
    private final int id = taskCount++;
    // 如果用static修饰id则表示为类常量，即所有对象下id总是为0
    // private static final int id=taskCount++;

    public RunnerA(){
        System.out.println("startup::ID=" + id);
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Task_" + id + "run()");
            Thread.yield();
        }
        System.out.println("Task_" + id + "completed");
    }
    // Output: (Sample)
    // startup::ID=0
    // startup::ID=1
    // Task_0run()
    // startup::ID=2
    // Task_1run()
    // startup::ID=3
    // Task_2run()
    // startup::ID=4
    // Task_3run()
    // Task_4run()
    // Task_0run()
    // Task_1run()
    // Task_2run()
    // Task_3run()
    // Task_4run()
    // Task_0run()
    // Task_1run()
    // Task_2run()
    // Task_3run()
    // Task_4run()
    // Task_0completed
    // Task_1completed
    // Task_2completed
    // Task_3completed
    // Task_4completed
}
