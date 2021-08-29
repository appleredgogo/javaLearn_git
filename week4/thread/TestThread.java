package java0.thread;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author shizeyu
 * @date 2021/8/29-22:41
 */
public class TestThread {
    //第一种拿到返回值的方法
    private static int currentNum = 0;

    public static void main(String[] args) {
        //第二种拿到返回值的方法
        final AtomicInteger num01 = new AtomicInteger(0);
        //第三种拿到返回值的方法
        final AtomicBoolean flag = new AtomicBoolean(false);
        //第四种拿到返回值的方法
        final AtomicLong num02 = new AtomicLong(0);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num01.set(1);
                flag.set(true);
                currentNum = 100;
                num02.set(200);
            }
        });
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行结果:"+num01);
        System.out.println("执行结果:"+flag);
        System.out.println("执行结果:"+currentNum);
        System.out.println("执行结果:"+num02);
    }
}
