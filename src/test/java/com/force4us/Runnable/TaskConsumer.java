package com.force4us.Runnable;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @Author: 孙海军【haijun.sun@jrj.com.cn】
 * @Date: 2017/10/28
 * @Time: 18:03
 **/
public class TaskConsumer implements Runnable {
    Jedis jedis = new Jedis("120.55.195.177",6379);

    public void run() {
        Random random = new Random();

        while(true){

            //从任务队列"task-queue"中获取一个任务，并将该任务放入暂存队列"tmp-queue"
            String taskid = jedis.rpoplpush("task-queue", "tmp-queue");

            // 处理任务----纯属业务逻辑，模拟一下：睡觉
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //模拟成功和失败的偶然现象
            if(random.nextInt(13) % 7 == 0){// 模拟失败的情况,概率为2/13
                //将本次处理失败的任务从暂存队列"tmp-queue"中，弹回任务队列"task-queue"

                /*这里补充一下，消费者使用
                jedis.rpoplpush(“tmp-queue”, “task-queue”);异常弹回队列task-queue会造成其中的任务顺序混乱，如果要求任务顺序正确的话，
                可以用jedis.rpush("task-queue",jedis.rpop("tmp-queue"));
                替代jedis.rpoplpush(“tmp-queue”, “task-queue”);*/

                jedis.rpoplpush("tmp-queue", "task-queue");
                System.out.println(taskid + "处理失败，被弹回任务队列");

            } else {// 模拟成功的情况

                // 将本次任务从暂存队列"tmp-queue"中清除
                jedis.rpop("tmp-queue");
                System.out.println(taskid+"处理成功，被清除");

            }
        }

    }
}
