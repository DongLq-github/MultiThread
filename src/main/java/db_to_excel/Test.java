package db_to_excel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        String sql = "select count(id) from log";
        int count = 0;
        int filesNum = 0;
        try {
            conn = DbConnection.getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()){
                count = rs.getInt(1);
            }
            rs.close();
            stat.close();
            conn.close();
            System.out.println("主线程成功读取数据库表记录数量为："+count);
        }catch (Exception e){
            e.printStackTrace();
        }

        //计算要创建多少个文件
        filesNum = count%500!=0?count/500+1:count/500;
        System.out.println("主线程计算到需要创建文件数为："+filesNum);

        //创建线程池
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        //执行多线程
        System.out.println("主线程开始分配多个线程任务···");
        long start = System.currentTimeMillis();
        for (int i = 0; i < filesNum; i++) {
            MyThread thread = new MyThread(i);
            executor.execute(thread);
        }

        //关闭线程池
        executor.shutdown();
        for (;;) {
            if (executor.isTerminated()){
                long end = System.currentTimeMillis();
                System.out.println("多线程执行耗时："+(end-start)+"ms");
                System.out.println("线程池成功关闭···");
                return;
            }
        }

    }

}
