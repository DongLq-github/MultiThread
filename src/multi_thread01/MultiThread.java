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

}
