package com.force4us.SeckillRunnable;

/**
 * @Author: 孙海军【haijun.sun@jrj.com.cn】
 * @Date: 2017/10/28
 * @Time: 11:52
 **/
public class RunnableTest implements Runnable {
    private int tick = 60;

    public void run() {
        while (true) {
            synchronized (this) {
                if (tick == 0) {
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "=========" + tick--);
            }
        }
    }
    public static void main(String[] args) {
        RunnableTest runnableTest=new RunnableTest();
        new Thread(runnableTest).start();
        new Thread(runnableTest).start();

    }
}