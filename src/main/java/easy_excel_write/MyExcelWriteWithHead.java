package easy_excel_write;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MyExcelWriteWithHead {

    public static void main(String[] args) {
        OutputStream out = null ;
        try {
            out = new FileOutputStream("D:/test_write_head.xlsx");
            ExcelWriter writer = new ExcelWriter(out,ExcelTypeEnum.XLSX,true);
            //表头
            List<List<String>> heads = new ArrayList<List<String>>();
            List<String> head01 = new ArrayList<String>();
            List<String> head02 = new ArrayList<String>();
            List<String> head03 = new ArrayList<String>();
            List<String> head04 = new ArrayList<String>();
            List<String> head05 = new ArrayList<String>();
            List<String> head06 = new ArrayList<String>();
            List<String> head07 = new ArrayList<String>();
            List<String> head08 = new ArrayList<String>();
            List<String> head09 = new ArrayList<String>();
            List<String> head10 = new ArrayList<String>();
            head01.add("第1列");
            head01.add("第1列");
            head01.add("第11列");
            head02.add("第1列");
            head02.add("第1列");
            head02.add("第12列");
            head03.add("第2列");
            head03.add("第21列");
            head03.add("第211列");
            head04.add("第2列");
            head04.add("第21列");
            head04.add("第212列");
            head05.add("第2列");
            head05.add("第22列");
            head05.add("第221列");
            head06.add("第2列");
            head06.add("第22列");
            head06.add("第222列");
            head07.add("第7列");
            head08.add("第8列");
            head09.add("第9列");
            head10.add("第10列");
            heads.add(head01);
            heads.add(head02);
            heads.add(head03);
            heads.add(head04);
            heads.add(head05);
            heads.add(head06);
            heads.add(head07);
            heads.add(head08);
            heads.add(head09);
            heads.add(head10);

            //表数据
            List<List<String>> list = new ArrayList<List<String>>();
            for (int i = 0; i < 2030; i++) {
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
                item.add(i+"行100列");
                list.add(item);
            }

            //sheet，可以设置sheet名，设置表头
            Sheet sheet = new Sheet(1,0);
            sheet.setSheetName("我的sheet");
            sheet.setHead(heads);

            System.out.println("开始写入数据到excel·····");
            writer.write0(list,sheet);
            writer.finish();
            System.out.println("写入成功！");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
