package java0.thread;

import java.util.Random;
import java.util.concurrent.*;

public class TestFuture {
    public static void main(String[] args) {
        //第五种拿到返回值的方法
        final ExecutorService executor01 = Executors.newCachedThreadPool();
        Future<Integer> result01 = executor01.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                Integer i = 200;
                return i;
            }
        });
        executor01.shutdown();
        try {
            System.out.println("执行结果:" + result01.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //第六种拿到返回值的方法
        FutureTask<Integer> task01 = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Integer i = 300;
                return i;
            }
        });
        new Thread(task01).start();

        try {
            System.out.println("执行结果:" + task01.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //第七种拿到返回值的方法
        final ExecutorService executor02 = Executors.newSingleThreadExecutor();
        FutureTask<Integer> task02 = new FutureTask<Integer>(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                Integer i = 400;
                return i;
            }
        });
        executor02.submit(task02);
        try {
            System.out.println("执行结果:"+task02.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //第八种拿到返回值的方法
        String result02 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "结果1";
        }).exceptionally(e->{
            return "结果2";
        }).join();
        System.out.println("执行结果:"+result02);
    }
}