package thread_callable;

import java.util.concurrent.FutureTask;

public class Test {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        FutureTask<String> ft = new FutureTask<String>(myThread);
        FutureTask<String> ft2 = new FutureTask<String>(myThread);
        Thread td = new Thread(ft);
        Thread td2 = new Thread(ft2);
        td.start();
        td2.start();
        try {
            System.out.println("ft返回结果为"+ft.get());
            System.out.println("ft2返回结果为"+ft2.get());
        }catch (Exception e){
            System.out.println("捕获异常");
        }

    }

}
