package thread_runnable;

public class Test {

    public static void main(String[] args) {
        MultiThread multiThread = new MultiThread();
        Thread t1 = new Thread(multiThread);
        Thread t2 = new Thread(multiThread);
        Thread t3 = new Thread(multiThread);
        t1.setName("线程1---");
        t1.start();
        t2.setName("线程2---");
        t2.start();
        t3.setName("线程3---");
        t3.start();
    }

}
