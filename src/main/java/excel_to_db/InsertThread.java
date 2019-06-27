package excel_to_db;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class InsertThread implements Runnable {

    private File[] files;

    public InsertThread(File[] files){
        this.files = files;
    }

    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();
        System.out.println(threadName+": 开始执行···");

        try {
            InputStream in = null;
            ExcelReader reader;
            ExcelListener listener;

            System.out.println(threadName+": 开始循环读取文件···");
            for (int i = 0; i < files.length; i++) {
                in = new FileInputStream(files[i]);
                System.out.println(threadName+": 成功创建输入流···");
                listener = new ExcelListener(files[i].getName());
                reader = new ExcelReader(in,ExcelTypeEnum.XLS,listener);
                reader.read(new Sheet(1,1));
            }

            in.close();
            System.out.println(threadName+": 线程结束执行！");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
