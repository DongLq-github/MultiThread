package easy_excel_read;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.FileInputStream;
import java.io.InputStream;

public class MyExcelRead {
    public static void main(String[] args) {
        InputStream input = null;
        try {
            input = new FileInputStream("D:/test_write_head.xls");
            ExcelListener listener = new ExcelListener();
            ExcelReader reader = new ExcelReader(input,ExcelTypeEnum.XLS,null,listener);
            //设置表头
            reader.read(new Sheet(1,3));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                input.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
