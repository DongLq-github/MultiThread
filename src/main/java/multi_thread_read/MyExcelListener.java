package multi_thread_read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MyExcelListener extends AnalysisEventListener {

    String path = "";
    String fileName = "";
    List<List<String>> datas = new ArrayList<List<String>>();

    public MyExcelListener(String path){
        this.path = path;
        int temp = path.lastIndexOf(".");
        this.fileName = path.substring(0,temp);
    }

    @Override
    public void invoke(Object object, AnalysisContext analysisContext) {
        List<String> list = (ArrayList<String>)object;//强转
        //移除不想要的列
        list.remove(18);
        list.remove(17);
        //list.set(16,list.get(16)+"\r\n");
        list.remove(15);
        list.remove(14);
        list.remove(13);
        list.remove(12);
        list.remove(11);
        list.add("\r\n");
        datas.add(list);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println(Thread.currentThread()+"文件读取成功····"+path);
        OutputStream out = null;
        BufferedOutputStream bos = null;
        try {
            out = new FileOutputStream(fileName+".txt");
            bos = new BufferedOutputStream(out);
            System.out.println(Thread.currentThread()+"开始写入文件数据"+fileName+".txt");
            bos.write(datas.toString().getBytes("utf-8"));
            System.out.println(Thread.currentThread()+"写入 "+fileName+".txt 成功！");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bos.close();
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
