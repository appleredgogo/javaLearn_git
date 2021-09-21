package io.github.shizeyu.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author shizeyu
 * @date 2021/9/22-23:54
 */
public class HikariMasterSlaveUtil {
    private static HikariDataSource maindb;
    private static HikariDataSource secondarydb;

    private static HikariDataSource ds;
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/shop?characterEncoding=utf8");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        maindb = new HikariDataSource(config);
    }
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3307/shop?characterEncoding=utf8");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        secondarydb = new HikariDataSource(config);
    }
    public static Connection getMainConnection() throws SQLException {
        return maindb.getConnection();
    }

    public static Connection getSecondaryConnection() throws SQLException {
        return secondarydb.getConnection();
    }
}
