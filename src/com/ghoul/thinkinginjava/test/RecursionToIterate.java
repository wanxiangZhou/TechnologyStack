package com.ghoul.thinkinginjava.test;

/**
 * @description: 递归与迭代</br>
 * 递归：感觉更抽象一些 </br>
 * 迭代：</br>
 * 简化代码设计，递归跟非递归方法相比，需要很消耗更多空间和时间，递归需要消耗系统的栈堆，当递归的深度达到一定量时就会造成系统的堆栈溢出，也可得知也会消耗大量的时间。
 * @author: wanxiangZhou
 * @e-mail: jframe@163.com
 * @date: Created in 2020/2/19 8:13 下午
 */
public class RecursionToIterate {
    // 为了让耗时差异更明显，可以在方法中均添加一条打印语句，保证语句内容、打印次数均一致；或者Thread.sleep()都可以
    // 绝大部分测试结果表明：递归耗时与迭代循环，相同代码测试，不分伯仲，都有执行更快的时候，那为啥说递归不如迭代呢？？
    // 但应该可以明确的是，递归确实比较容易消耗系统堆栈。另外，是不是应该都有使用场景限制，例如：
    // "如果递归的是深而不广的树，应该使用队列来优化递归；如果递归是广而不深的树，就应该用递归来优化队列。"

    /**
     * 递归，计算阶乘
     * 
     * @param number 参数，正整数
     * @return 阶乘结果
     */
    public static long recursion(int number) {
        if (number == 1) {
            return 1;
        }
        return number * recursion(number - 1);
    }

    /**
     * 迭代、循环，计算阶乘
     * 
     * @param number 参数，正整数
     * @return 阶乘结果
     */
    public static long iterate(int number) {
        long sum = 1;
        for (int i = 1; i <= number; i++) {
            // sum += sum * i;错啦
            sum *= i;
        }
        return sum;
    }

    public static void main(String[] args) {
        // 测试20次
        for (int i = 0; i < 20; i++) {
            long recursionStart = System.currentTimeMillis();
            System.out.println(recursion(20));
            System.out.println("递归方式耗时（单位：毫秒）：" + (System.currentTimeMillis() - recursionStart));
            long iterateStart = System.currentTimeMillis();
            System.out.println(iterate(20));
            System.out.println("迭代循环耗时（单位：毫秒）：" + (System.currentTimeMillis() - iterateStart));
        }
    }
}
