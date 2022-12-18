package org.example;

import java.sql.*;

// checked -- vf la compile time
// unchecked -- aruncata la runtime

public class JdbcExample {
    public static void main(String[] args) {
        //jdbc: mysql://localhost:3306/flower_shop
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flower_shop", "root","root");
            Statement statement = connection.createStatement();
           ResultSet rs  = statement.executeQuery("Select * from product where id = 1");
           rs.next();
            System.out.println(rs.getString(2) + " " + rs.getString(4));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}