package io.github.shizeyu.preparedStatement;



import io.github.shizeyu.jdbc.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author shizeyu
 * @date 2021/9/6-23:36
 */
public class PreparedStatementSqlData {
    public static void main(String[] args) throws SQLException {
        //取数据库连接对象
        Connection cn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        //接收对象
        ResultSet rs = null;
        //查询
        String sql1 = "select * from student where id = ?";
        ps = cn.prepareStatement(sql1);
        ps.setObject(1, "10");
        query(ps,rs);
        //增加
        String sql2 = "insert into student(id,name) values(?,?)";
        ps = cn.prepareStatement(sql2);
        ps.setObject(1, "20");
        ps.setObject(2, "xiaoming");
        System.out.println("插入执行结果:"+update(ps,sql2));
        //更新
        String sql3 = "update student set name=? where id = ?";
        ps = cn.prepareStatement(sql3);
        ps.setObject(1, "xm");
        ps.setObject(2, "20");
        System.out.println("更新执行结果:"+update(ps,sql3));
        //删除
        String sql4 = "delete from student WHERE id = ?";
        ps = cn.prepareStatement(sql4);
        ps.setObject(1, "20");
        System.out.println("删除执行结果:"+update(ps,sql4));
        JDBCUtils.close(rs,cn,ps);
    }
    /**
     * 查询
     */
    private static void query(PreparedStatement st, ResultSet rs) throws SQLException {
        rs = st.executeQuery();
        while(rs.next()) {
            System.out.println(rs.getObject("name"));
        }
    }
    /**
     * 增删改
     */
    private static int update(PreparedStatement st,String sql) throws SQLException {
        return st.executeUpdate();
    }
}
