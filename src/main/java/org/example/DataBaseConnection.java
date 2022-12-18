package org.example;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/flower_shop");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            try {
                connection = dataSource.getConnection();
                return connection;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else {
            return connection;
        }
    }
}
