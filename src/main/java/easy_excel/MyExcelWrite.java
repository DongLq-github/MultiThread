package easy_excel;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MyExcelWrite {

    public static void main(String[] args) {

        //创建输出流
        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream("D:\\test_excel_write.xlsx");
            /**
             * 输出流：outputStream
             * excel类型：ExcelTypeEnum.XLSX
             * 是否需要表头：false
             */
            ExcelWriter excelWrite = new ExcelWriter(outputStream,ExcelTypeEnum.XLSX,false);

            //新建一个sheet, sheet数据是List<String>
            Sheet sheet01 = new Sheet(1,0);
            sheet01.setSheetName("第一个sheet");

            //创建List数据
            List<List<String>> list = new ArrayList<List<String>>();
            for (int i = 0; i < 200; i++) {
                List<String> item = new ArrayList<String>();
                item.add(i+"行1列");
                item.add(i+"行2列");
                item.add(i+"行3列");
                item.add(i+"行4列");
                item.add(i+"行5列");
                item.add(i+"行6列");
                item.add(i+"行7列");
                item.add(i+"行8列");
                item.add(i+"行9列");
                item.add(i+"行10列");
                list.add(item);
            }
            excelWrite.write0(list,sheet01);
            excelWrite.finish();
        }catch (FileNotFoundException e){
            System.out.println("捕获异常：文件未找到");
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            }catch (Exception e){
                System.out.println("关闭输出流异常！");
                e.printStackTrace();
            }
        }

    }

}
