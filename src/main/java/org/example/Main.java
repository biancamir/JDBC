package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            //display all products
            Statement statement = DataBaseConnection.getConnection().createStatement();
            int rowCount = statement.executeUpdate("insert into product (name, product_type, price, quantity) values" +
                    "('Ivy', 'Flower', 20.5, 100); ");
            System.out.println("Row count = " + rowCount);
            // set de randuri
            ResultSet productSet = statement.executeQuery("SELECT*FROM product");
            while (productSet.next()) {
                System.out.print("Product:\t" + productSet.getString(2));
                System.out.print(" with price:\t" + productSet.getDouble(4));
                System.out.println(" and quantity:\t" + productSet.getInt(5));
            }
            ResultSet mostExpensiveProduct = statement.executeQuery(
                    "SELECT * FROM PRODUCT WHERE price = (SELECT MAX(price) FROM product)");
            mostExpensiveProduct.next();
            System.out.println("Most expensive product is " + mostExpensiveProduct.getString(2)
                    + "with a price of " + mostExpensiveProduct.getDouble(4));

            displayAllProductsFrom("Alexandru");
            displayAllFlowers(10, 20);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void displayAllProductsFrom(String supplierName) throws SQLException {
        Statement statement = DataBaseConnection.getConnection().createStatement();
        ResultSet result = statement.executeQuery("select supplier.name, product.name " +
                "from supplier " +
                "join supplier_product_connection as con on supplier.id = con.supplier_id " +
                "join product on con.product_id = product.id " +
                "where supplier.name = '" + supplierName + "';");
        while (result.next()) {
            System.out.print("Supplier name : " + result.getString(2) + "Product name " + result.getString(2));
        }
    }

    static void displayAllFlowers(int quantity, double price) throws SQLException {
        Statement statement = DataBaseConnection.getConnection().createStatement();
        ResultSet result = statement.executeQuery(" select *" +
                "from product where quantity >= " + quantity + " " +
                "and price <= " + price + ";");
        while (result.next()) {
            System.out.println(result.getString(1) + " "
                    + result.getDouble(3) + "RON" + result.getInt(2) + "buc");
        }
    }
    //all flowers that are more than Quantity Q and cost less than Price P

    //tre sa precizez ordinea
}
