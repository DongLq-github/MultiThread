package excel_to_db;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import db_to_excel.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener {

    private String sql = "insert into excel_to_db values(0,?,?,?,?,?,?,?,?)";
    private Connection conn = DbConnection.getConnection();
    private PreparedStatement ps = null;
    private int row = 1;
    private String fileName = "";
    private String threadName = Thread.currentThread().getName();

    public ExcelListener(String fileName){
        this.fileName = fileName;
    }

    /**
     * 每分析完一行数据都会执行一次invoke()方法
     * @param object
     * @param analysisContext
     */
    @Override
    public void invoke(Object object, AnalysisContext analysisContext) {
        try {
            if (row == 1) ps = conn.prepareStatement(sql);
            row++;
            List<String> list = (ArrayList<String>)object;//强转
            ps.setString(1,list.get(1));
            ps.setInt(2,Integer.parseInt(list.get(2).equals("")?(0+""):list.get(2)));
            ps.setString(3,list.get(3));
            ps.setString(4,list.get(4));
            ps.setString(5,list.get(5));
            ps.setString(6,list.get(6));
            ps.setString(7,list.get(7));
            ps.setString(8,list.get(8));
            ps.addBatch();
        }catch (NumberFormatException e){
            System.out.println(threadName+": 数据格式转换错误！！！"+fileName+row+e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 整个文件分析完之后执行doAfterAllAnalysed()方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println(threadName+": 成功读取分析文件 "+fileName);
        System.out.println(threadName+": 开始进行数据持久化···");
        try {
            int[] array = ps.executeBatch();
            //计算插入成功的记录数量
            System.out.println(threadName+": 成功持久化文件 "+fileName);
            System.out.println(threadName+": 插入数据库记录："+array.length);
            //加到主线程的总数量上
            synchronized (this){
                Test.rows_insert = Test.rows_insert+array.length;
            }
            ps.close();
            conn.close();
        }catch (Exception e){
            System.out.println(threadName+": 捕获异常！当前写入的文件为 "+fileName);
            e.printStackTrace();
        }
    }
}
