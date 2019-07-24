package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {

    final static String DATABASE = "jdbc:mysql://localhost:3306/szkola?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    final static String USER = "root";
    final static String PASSWORD = "coderslab";

    public static Connection getConnection() throws SQLException {
        System.out.println("ddd");
            return DriverManager.getConnection(DATABASE, USER, PASSWORD);
    }

}
