package excel_to_db;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

        InputStream in = null;
        ExcelReader reader;
        ExcelListener listener;

        try {
            System.out.println(threadName+": 开始循环读取文件···");
            for (int i = 0; i < files.length; i++) {
                in = new FileInputStream(files[i]);
                System.out.println(threadName+": 成功创建输入流···");
                listener = new ExcelListener(files[i].getName());
                reader = new ExcelReader(in,ExcelTypeEnum.XLS,null,listener);
                reader.read(new Sheet(1,1));
            }
            System.out.println(threadName+": 线程结束执行！");
        }catch (FileNotFoundException e){
            System.out.println(threadName+": 未找到文件···");
            System.out.println(threadName+": "+e.getMessage());
        }catch (Exception e){
            System.out.println(threadName+": 捕获异常！！！"+e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                in.close();
            }catch (Exception e){
                System.out.println(threadName+": 关闭输入流失败···");
                e.printStackTrace();
            }
        }
    }
}
