package util;


import java.sql.*;

/**
 * Created by root on 17-3-15.
 */
public class Mysql2TextUtil {
    public static void main(String[] args){
        mysql2Txt();
    }
    private static void mysql2Txt(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/test";
            Connection conn;
            conn=DriverManager.getConnection(url,"root","root");
            Statement statement=conn.createStatement();
            String sql = "select * from t_tumblr";
            ResultSet rs = statement.executeQuery(sql);//创建数据对象
            System.out.println("编号"+"\t"+"姓名"+"\t"+"年龄");
            while (rs.next()){
                System.out.print(rs.getString(2) + "\t");
                System.out.print(rs.getString(3) + "\t");
                System.out.print(rs.getString(4) + "\t");
                System.out.println();
            }
            rs.close();
            statement.close();
            conn.close();
        }catch (Exception e){

        }
    }

}
