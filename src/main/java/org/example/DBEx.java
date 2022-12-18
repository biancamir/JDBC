package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBEx {
    public static void main(String[] args) {
        Connection connection;
        connection = DataBaseConnection.getConnection();
        createTableMarketingCampaign(connection);
        initializeMarketingCampaign(connection);

    }
    // create a table

    public static void createTableMarketingCampaign(Connection connection) {

        try {
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists marketing_campaign (" +
                    "id int primary key auto_increment," +
                    "name varchar (200)," +
                    "start_date date," +
                    "budget double);");
        } catch (SQLException e) {
            System.out.print("Error creating table marketing_campaign");
            throw new RuntimeException(e);
        }
    }

    //insert a row/multiple rows in a table if the table is empty
    public static void initializeMarketingCampaign(Connection connection) {
        Statement statement = null;
        try {
            statement = DataBaseConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) from marketing_campaign;");
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                statement.execute("insert into marketing_campaign (name, start_date, budget) values" +
                        "('Name1', '2022-12-18', 1000) , ('Name2', '2022-10-15',2000),('Name3', '2022-10-20',500) ");
                System.out.println("The table marketing_campaign was initialized!");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    //display all values in a table
    //search for a row by a value


}
