package easy_excel;

import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ExcelWrite {

    public void writeWithoutHead(){
        try {
            OutputStream outputStream = new FileOutputStream("D:\\test_excel_write.xlsx");
            ExcelWrite excelWrite = new ExcelWrite(outputStream,ExcelTypeEnum.XLSX,);

        }catch (FileNotFoundException e){
            System.out.println("捕获异常：文件未找到");
            e.printStackTrace();
        }

    }

}
