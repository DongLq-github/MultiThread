package excel_to_db;


import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    final static int THREAD_NUM = 5;//线程池线程数量
    final static int FILE_NUM_THREAD = 1;//每个线程需要处理的文件数量，默认1
    private static int threads = THREAD_NUM;//启动的线程任务数，默认与线程数量一致

    public static void main(String[] args) {

        File dir = new File("D:/excel/log");
        File[] files = dir.listFiles();
        System.out.println("读取到的文件数量为："+files.length);
        for (File file: files) {
            System.out.println(file.getAbsolutePath());
        }

        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_NUM);

        //计算需要启动多少个线程任务
        threads = files.length%FILE_NUM_THREAD==0?
                files.length/FILE_NUM_THREAD:files.length/FILE_NUM_THREAD+1;

        System.out.println("开始启动多线程···");
        for (int i = 0; i < threads; i++) {
            System.out.println("开始给分配任务给线程 "+i);
            File[] files1 = new File[FILE_NUM_THREAD];
            for (int j = i*FILE_NUM_THREAD,k=0; j < (i+1)*FILE_NUM_THREAD; j++,k++) {
                if (j == files.length) break;
                files1[k] = files[j];
            }
            InsertThread thread = new InsertThread(files1);
            executor.execute(thread);
        }

        for (;;){
            if (executor.isTerminated()){
                System.out.println("线程池已经关闭···");
                return;
            }
        }
    }
}
