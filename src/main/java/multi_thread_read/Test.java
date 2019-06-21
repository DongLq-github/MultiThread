package multi_thread_read;

import java.io.File;
import java.util.Stack;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    static Stack<String> stack = new Stack<String>();

    public static void main(String[] args) {
        String path = "D:/excel";
        File dir = new File(path);
        File[] files = dir.listFiles();
        //文件路径入栈
        if (files != null){
            for (File file : files) {
                //xls结尾的文件入栈
                if (file.getName().endsWith("xls")){
                    stack.add(file.getAbsolutePath());
                }
            }
        }
        System.out.println("文件绝对路径入栈完成····");

        //创建线程池
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        while (!stack.isEmpty()){
            MyReadThread thread = new MyReadThread(stack.pop());
            executor.execute(thread);
        }
        //关闭线程池
        executor.shutdown();
        for (;;){
            if (executor.isTerminated()){
                System.out.println("线程池关闭····");
                return;
            }
        }
    }

}
