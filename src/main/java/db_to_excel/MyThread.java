package db_to_excel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyThread implements Runnable {

    private int logsNum = 0;

    public MyThread(int logsNum){
        this.logsNum = logsNum;
    }

    @Override
    public void run() {
        try {
            //获取数据库连接
            Connection conn = DbConnection.getConnection();
            String sql = "select " +
                    "l.id, l.time, l.etime, l.content, l.explain, l.tel," +
                    "r.role role, u.real_name, u.student_id, s.name schname" +
                    "from log l left join role r on l.role_id = r.id " +
                    "left join user u on l.user_id = u.id " +
                    "left join school s on u.school = s.id limit ?,500";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,logsNum*1000);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
