package io.github.shizeyu.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author shizeyu
 * @date 2021/9/6-23:54
 */
public class HikariUtil {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    static {
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/shop?characterEncoding=utf8");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
        config = new HikariConfig();
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
