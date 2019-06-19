package thread_callable;

import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

public class TestCallablePool {

    public static void main(String[] args) {
        //创建线程池
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        MyThread thread = new MyThread();

        //线程启动
        for (int i = 0; i < 1000; i++) {

            //多少个线程就要有多少个futrueTask对象
            FutureTask task = new FutureTask(thread);

            executor.execute(task);
            /*System.out.println("线程池中线程数量："+executor.getPoolSize()+
                    ", 等待的任务数量："+executor.getQueue().size()+
                    ", 已经执行完的任务："+executor.getCompletedTaskCount());*/
        }
        try{
            Thread.sleep(3000);
            executor.shutdown();
            System.out.println("线程池关闭");
            System.out.println("线程池中线程数量："+executor.getPoolSize()+
                    ", 等待的任务数量："+executor.getQueue().size()+
                    ", 已经执行完的任务："+executor.getCompletedTaskCount());
        }catch (Exception e){

        }

    }
}
