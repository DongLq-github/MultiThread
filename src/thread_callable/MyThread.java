package thread_callable;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyThread implements Callable<String> {

    private volatile int number = 1000;

    //读写锁
    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock writeLock = lock.writeLock();

    @Override
    public String call() throws Exception {
        //System.out.println("线程"+Thread.currentThread().getName()+"正在运行");
        writeLock.lock();//上锁
        System.out.println("当前车票余量为"+number+"张");
        if (number > 0) number--;
        writeLock.unlock();//解锁
        return "运行结束";
    }
}
