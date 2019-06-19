package thread_pool;

import java.util.concurrent.*;

public class Test {

    static int i = 0;

    public static void main(String[] args) {
        /**
         * 核心线程数量：5
         * 最大线程数量：8
         * 空闲超时存活：1s
         * 等待队列最大任务数：50
         * ···java不推荐此方式手动创建线程池
         */
        /*ThreadPoolExecutor executor = new ThreadPoolExecutor(5, //核心线程数
                8,                                                //最大线程数
                1,                                                //线程空闲存活时间
                TimeUnit.SECONDS,                                 //超时时间单位
                new LinkedBlockingDeque<>(50));*/                 //任务队列以及任务等待队列的最大任务数

        /**
         * java推荐的创建方式，通过静态方法创建
         * @param nThread线程数
         */
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        try {
            for (int i=0;i<1000;i++){
                Test.i = i;
                MyThread td = new MyThread(i);
                executor.execute(td);
                System.out.println("线程池中线程数量："+executor.getPoolSize()+
                        ", 等待的任务数量："+executor.getQueue().size()+
                        ", 已经执行完的任务："+executor.getCompletedTaskCount());
            }
            //主线程睡眠4秒
            Thread.sleep(4000);
            //再次读取线程池的线程数量
            System.out.println("线程池中线程数量："+executor.getPoolSize()+
                    ", 等待的任务数量："+executor.getQueue().size()+
                    ", 已经执行完的任务："+executor.getCompletedTaskCount());
            //关闭线程池
            executor.shutdown();
            System.out.println("线程池已关闭！");
            System.out.println("线程池中线程数量："+executor.getPoolSize()+
                    ", 等待的任务数量："+executor.getQueue().size()+
                    ", 已经执行完的任务："+executor.getCompletedTaskCount());
        }catch (RejectedExecutionException e){
            System.out.println("线程超限，拒绝执行线程"+Test.i+"!");
        }catch (InterruptedException e){
            System.out.println("线程中断！");
        }


    }

}
