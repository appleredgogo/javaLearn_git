package io.github.shizeyu.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author shizeyu
 * @date 2021/9/6-23:16
 */
public class OperationSqlData {

    public static void main(String[] args) {
        saveDatas();
        updDatas();
        selectDatas();
        delDatas();
    }
    //查询操作
    public static void selectDatas(){
        ResultSet rs = null;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.createStatement();
            //用接收查询的数据
            rs = statement.executeQuery("select * from student");
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("查询数据:"+id+"--"+name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JDBCUtils.close(rs,connection,statement);
        }
    }
    //新增操作
    public static void saveDatas(){
        ResultSet rs = null;
        Connection connection = null;
        Statement statement = null;
        try {
            connection =JDBCUtils.getConnection();
            statement = connection.createStatement();
            String sql = "insert into student (id,name) values (10 ,'shizeyu')";
            //执行增删改操作，返回值为int
            System.out.println("新增操作:"+ statement.executeUpdate(sql));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //6.释放资源
            JDBCUtils.close(rs,connection,statement);
        }
    }
    //删除操作
    public static void delDatas(){
        ResultSet rs = null;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.createStatement();
            String sql = "delete from student where id = 10";
            //执行删除，返回值为int
            System.out.println("删除操作:"+ statement.executeUpdate(sql));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JDBCUtils.close(rs,connection,statement);
        }
    }
    //修改操作
    public static void updDatas(){
        ResultSet rs = null;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.createStatement();
            String sql = "update student set name='szy' where id =10";
            //执行改操作
            System.out.println("修改操作:"+ statement.executeUpdate(sql));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,connection,statement);
        }
    }
}
