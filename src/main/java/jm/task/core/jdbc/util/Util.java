package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String HOST = "jdbc:mysql://localhost:3306/users?serverTimezone=Europe/Moscow&useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "22111993";

    public Connection connection = null;
    public Util() {
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            System.out.println("Соединение установлено");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
