package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String HOST = "jdbc:mysql://localhost:3306/users?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "22111993";


    public Util() {
    }
    //TODO JDBC
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             connection.setAutoCommit(false);
            System.out.println("Подключение успешно");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Ошибка соединения");
        }
        return connection;
    }

    //TODO HIBER
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
            System.out.println("Соединение созданно");
        } catch (Exception e) {
            System.err.println("Ошибка соединения " + e.getMessage());
        }
        return sessionFactory;
    }

}
