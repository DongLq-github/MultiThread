package db_to_excel;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyThread implements Runnable {

    private int logsNum = 0;
    final String path = "D:/excel/log/";

    public MyThread(int logsNum){
        this.logsNum = logsNum;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName+": 开始运行···");
        try {
            //获取数据库连接
            Connection conn = DbConnection.getConnection();
            String sql = "select " +
                    "l.id, l.time, l.etime, l.content, l.explain, l.tel," +
                    "r.role role, u.real_name, u.student_id, s.name schname " +
                    "from log l left join role r on l.role_id = r.id " +
                    "left join user u on l.user_id = u.id " +
                    "left join school s on u.school = s.id limit ?,500";
            PreparedStatement ps = conn.prepareStatement(sql);
            System.out.println(threadName+": 已获取到数据库连接···");

            //设置起始行数
            if (logsNum != 0) logsNum = logsNum*500;
            ps.setInt(1,logsNum);

            //从ps获得执行的SQL语句
            int index = ps.toString().indexOf(": ")+2;
            String doSql = ps.toString().substring(index);
            System.out.println(threadName+": 执行的sql: "+doSql);

            ResultSet rs = ps.executeQuery();

            //创建输出流
            String fileName = "log"+logsNum+".xls";
            OutputStream out = new FileOutputStream(path+fileName);
            ExcelWriter writer = new ExcelWriter(out,ExcelTypeEnum.XLS,true);
            System.out.println(threadName+": 已创建输出流···");

            //生成excel表头
            List<List<String>> head = new ArrayList<List<String>>();
            head.add(Arrays.asList("记录id"));
            head.add(Arrays.asList("操作时间"));
            head.add(Arrays.asList("执行耗时"));
            head.add(Arrays.asList("操作内容"));
            head.add(Arrays.asList("描述"));
            head.add(Arrays.asList("电话"));
            head.add(Arrays.asList("用户角色"));
            head.add(Arrays.asList("真实姓名"));
            head.add(Arrays.asList("学生编号"));
            head.add(Arrays.asList("学校名"));

            //设置表数据
            List<List<String>> datas = new ArrayList<List<String>>();
            //从数据集取出数据
            System.out.println(threadName+": 开始从数据集rs获取数据到datas···");
            while(rs.next()){
                List<String> data = new ArrayList<String>();
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getString(4));
                data.add(rs.getString(5));
                data.add(rs.getString(6));
                data.add(rs.getString(7));
                data.add(rs.getString(8));
                data.add(rs.getString(9));
                data.add(rs.getString(10));
                datas.add(data);
            }

            //设置表头到sheet
            Sheet sheet = new Sheet(1,1);
            sheet.setHead(head);

            //写入数据到excel
            System.out.println(threadName+"：开始写入数据到excel文件: "+fileName);
            writer.write0(datas,sheet);
            writer.finish();
            System.out.println(threadName+"：文件 "+fileName+"写入成功········");

            rs.close();
            ps.close();
            conn.close();
            System.out.println(threadName+": 成功关闭各种资源···");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
