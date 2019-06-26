package db_to_excel;

import java.sql.*;

public class DbConnection {

    static String url = "jdbc:mysql://localhost:3306/guangzi";
    static String driver = "com.mysql.jdbc.Driver";
    static String user = "root";
    static String password = "root";

    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection conn ;
        try {
            //加载数据库驱动
            Class.forName(driver);
            //创建连接
            conn = DriverManager.getConnection(url,user,password);

            if (conn != null){
                System.out.println("已获得jdbc连接····");
                System.out.println(conn);
            }

            /*String sql = "select * from log limit 0,10";
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(sql);*/

            String sql = "select * from log limit 0,?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,100);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.print(rs.getString(1)+"\t");
                System.out.print(rs.getString(2)+"\t");
                System.out.print(rs.getString(3)+"\t");
                System.out.print(rs.getString(4)+"\t");
                System.out.print(rs.getString(5)+"\t");
                System.out.print(rs.getString(6)+"\t");
                System.out.print(rs.getString(7)+"\t");
                System.out.print(rs.getString(8)+"\t");
                System.out.print(rs.getString(9)+"\t");
                System.out.print(rs.getString(10));
                System.out.println();
            }

            //关闭连接
            rs.close();
            ps.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
