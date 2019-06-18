package thread_pool;

import java.util.concurrent.RejectedExecutionException;

public class MyThread implements Runnable {

    private int number;

    public MyThread(int number){
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("线程"+number+"正在运行---");
    }
}
