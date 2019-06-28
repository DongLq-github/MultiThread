package excel_to_db;


import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    final static int THREAD_NUM = 5;//线程池线程数量
    final static int FILE_NUM_THREAD = 4;//每个线程需要处理的文件数量，默认1
    private static int threads = THREAD_NUM;//启动的线程任务数，默认与线程数量一致
    public static int rows_insert = 0;//线程写入数据库的记录总数

    public static void main(String[] args) {

        File dir = new File("D:/excel/log");
        File[] files = dir.listFiles();
        System.out.println("读取到的文件数量为："+files.length);
        for (File file1: files) {
            System.out.println(file1.getAbsolutePath());
        }

        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_NUM);

        //计算需要启动多少个线程任务
        threads = files.length%FILE_NUM_THREAD==0?
                files.length/FILE_NUM_THREAD:files.length/FILE_NUM_THREAD+1;

        System.out.println("开始启动多线程···");
        long start = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            System.out.println("开始分配线程任务 "+i);
            //每个线程任务要处理多少个文件就给多大的数组，否则容易空指针
            //最后一个线程任务要处理的文件数量为：文件总数 % 每条线程任务处理的文件数
            File[] files1 = null;
            if (i == threads-1){
                files1 = new File[files.length%FILE_NUM_THREAD];
            }else {
                files1 = new File[FILE_NUM_THREAD];
            }
            for (int j = i*FILE_NUM_THREAD,k=0; j < (i+1)*FILE_NUM_THREAD; j++,k++) {
                if (j == files.length) break;
                files1[k] = files[j];
            }
            InsertThread thread = new InsertThread(files1);
            executor.execute(thread);
        }

        executor.shutdown();
        for (;;){
            if (executor.isTerminated()){
                long end = System.currentTimeMillis();
                System.out.println("线程池已经关闭，线程总共耗时："+(end-start)+"ms");
                System.out.println("总共写入数据记录数："+rows_insert);
                return;
            }
        }
    }
}
