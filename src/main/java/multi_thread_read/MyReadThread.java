package multi_thread_read;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyReadThread implements Runnable {

    private String path;//文件路径名栈
    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock writeLock = lock.writeLock();//写锁

    public MyReadThread(String path){
        this.path = path;
    }

    @Override
    public void run() {
        //开始读文件
        InputStream in = null;
        try {
            in = new FileInputStream(path);
            System.out.println(Thread.currentThread()+"开始读文件····"+path);
            MyExcelListener listener = new MyExcelListener(path);
            ExcelReader reader = new ExcelReader(in,ExcelTypeEnum.XLS,null,listener);
            reader.read(new Sheet(1,1));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
