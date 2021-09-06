package io.github.shizeyu.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author shizeyu
 * @date 2021/9/6-22:55
 */
public class JDBCUtils {

    /**
     * 获取连接方法
     */
    public static Connection getConnection() {
        {
            //1.注册数据库驱动
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //2.获取连接
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
                return cn;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

    }
    /**
     * 关闭资源
     */
    public static void close(ResultSet rs,Connection connection,Statement statement) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
