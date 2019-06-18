package multi_thread01;


public class MultiThread implements Runnable {

    int number = 20;

    @Override
    public void run() {
        try {
            //synchronized (this){
                for ( ; number > 0; number--) {
                    System.out.println(Thread.currentThread().getName()+":"+number);
                    Thread.sleep(500);
                }
            //}
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

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
