package io.github.shizeyu.hikari;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author shizeyu
 * @date 2021/9/6-23:57
 */
public class TestHikari {
    public static void main(String[] args) throws SQLException {
        saveDatas();
        updDatas();
        queryDatas();
        delDatas();
    }
    //增
    public static void saveDatas() throws SQLException {
        int i = 0;
        Connection conn = HikariUtil.getConnection();
        String sql = "insert into student(id,name) values(?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, 10);
                ps.setString(2, "xiaoming");
                i = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        System.out.println(i);
    }
    //改
    public static void updDatas() throws SQLException {
        int i = 0;
        Connection conn = HikariUtil.getConnection();
        String sql = "update student set name=? where id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "xm");
            ps.setInt(2, 10);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
    //查
    public static void queryDatas() throws SQLException {
        int i = 0;
        Connection conn = HikariUtil.getConnection();
        String sql = "select * from student where id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, 10);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getObject("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
    //删
    public static void delDatas() throws SQLException {
        int i = 0;
        Connection conn = HikariUtil.getConnection();
        String sql = "delete from student WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, 10);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
}
