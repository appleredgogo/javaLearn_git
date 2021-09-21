package io.github.shizeyu.preparedStatement;



import io.github.shizeyu.jdbc.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author shizeyu
 * @date 2021/9/22-23:36
 */
public class InsertMillionSqlData {
    public static void main(String[] args) throws SQLException {
        //取数据库连接对象
        Connection cn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        //接收对象
        ResultSet rs = null;
        //开始时间
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间:"+startTime);
        //增加
        String sql = "insert into orders(id,user_id,order_list,status,total_price,logistics_status,create_time,update_time)" +
                " values(?,?,?,?,?,?,?,?)";
        // 连接对象
        cn.setAutoCommit(false);
        // 执行命令对象
        ps =  cn.prepareStatement(sql);
        for (int i = 0; i < 1000000; i++) {
            ps.setInt(1,i);
            ps.setInt(2,10);
            ps.setString(3,"212000001");
            ps.setString(4,"进行中");
            ps.setObject(5,2);
            ps.setString(6,"运输中");
            ps.setLong(7,System.currentTimeMillis());
            ps.setLong(8,System.currentTimeMillis());
            ps.addBatch();
        }
        ps.executeBatch();
        ps.clearBatch();
        cn.commit();
        //System.out.println("插入执行结果:"+update(ps,sql));
        long endTime = System.currentTimeMillis();
        System.out.println("结束时间:"+startTime);
        long sumtime = (endTime - startTime) / 1000;
        System.out.println("耗时：" + sumtime +" s ");
        JDBCUtils.close(rs,cn,ps);
    }

    /**
     * 增删改
     */
    private static int update(PreparedStatement st,String sql) throws SQLException {
        return st.executeUpdate();
    }

}
